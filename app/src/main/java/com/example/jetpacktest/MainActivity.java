package com.example.jetpacktest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private Button plus,clear,getUser,add,delete,update,query,doWork;
    private TextView textView;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private UserDatabase userDatabase;
    private UserDao userDao;
    User user1,user2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("data",MODE_PRIVATE);
        editor = sp.edit();
        int counter = sp.getInt("counter",0);
        //通过ViewModelProvider实例来得到ViewModel实例
        mainViewModel = new ViewModelProvider(this,new MainViewModelFactory(counter)).get(MainViewModel.class);

        bindViews();

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.plus();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.clear();
            }
        });

        getUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = getRandomString(5);
                mainViewModel.getUser(userId);
            }
        });

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.userDao();
        user1 = new User("Tom","Brady",22);
        user2 = new User("Andy","Hanks",33);

        //耗时逻辑，开启线程
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        user1.id = userDao.insertUser(user1);
                        user2.id = userDao.insertUser(user2);
                    }
                }).start();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        user1.setAge(24);
                        userDao.updateUser(user1);
                    }
                }).start();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userDao.deleteUserByLastName("Hanks");
                    }
                }).start();
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<User> list = userDao.loadAllUsers();
                        for(int i=0;i<list.size();i++){
                            User user = list.get(i);
                            Log.d("MainActivity",user.getFirstName()+"," + user.getLastName() + "," +user.getAge());
                        }
                    }
                }).start();
            }
        });

        doWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SimpleWorker.class).build();
                WorkManager.getInstance(MainActivity.this).enqueue(request);
            }
        });

        //对该LiveData进行观察，一旦发生变化就在TextView上进行显示
        mainViewModel.counterData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(integer.toString());
            }
        });

        mainViewModel.userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                textView.setText(user.getFirstName());
            }
        });
    }

    //当活动onPause()执行后，将数据存储在SharedPreferences中
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause","onPause执行了");
        if(mainViewModel.counterData.getValue() != null){
            editor.putInt("counter",mainViewModel.counterData.getValue());
        }else {
            editor.putInt("counter",0);
        }
        editor.apply();
    }

    private void bindViews() {
        plus = findViewById(R.id.plus);
        clear = findViewById(R.id.clear);
        getUser = findViewById(R.id.get_user);
        textView = findViewById(R.id.text_view);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        query = findViewById(R.id.query);
        doWork = findViewById(R.id.do_work);
    }

    public String getRandomString(int length){
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random=new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0; i<length; ++i){
            int number=random.nextInt(62);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }
}