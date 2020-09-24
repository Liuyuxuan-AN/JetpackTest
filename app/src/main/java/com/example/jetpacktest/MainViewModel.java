package com.example.jetpacktest;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    //先设置一个在本类中可进行修改的LiveData对象 _counterData
    private MutableLiveData<Integer> _counterData = new MutableLiveData<>();
    //再将上述的 _counterData对象传给外部调用的counterData对象，这样外部就无法对其数据进行更改
    final LiveData<Integer> counterData = _counterData;

    private MutableLiveData<User> userData = new MutableLiveData<>();
    private MutableLiveData<String> userIdData = new MutableLiveData<>();

    //对LiveData进行转换，将包含全部信息的LiveData利用map转换为只包含部分展示信息的LiveData
    final LiveData<String> userName = Transformations.map(userData, new Function<User, String>() {
        @Override
        public String apply(User input) {
            return input.getFirstName() + input.getLastName();
        }
    });

    //将原LiveData对象转换成另外一个可观察的LiveData对象
    final LiveData<User> userLiveData = Transformations.switchMap(userIdData, new Function<String, LiveData<User>>() {
        @Override
        public LiveData<User> apply(String input) {
            return Repository.getInstance().getUser(input);
        }
    });

    public MainViewModel(int counter){
        _counterData.setValue(counter);
    }

    //调用该方法时，userIdData中数据发生变化
    //此时switchMap方法调用，开始执行逻辑
    public void getUser(String userId){
        userIdData.setValue(userId);
    }

    public void plus(){
        int counter;
        if(_counterData.getValue() != null){
            counter = _counterData.getValue();
        }else {
            counter = 0;
        }
        _counterData.setValue(counter + 1);
    }

    public void clear(){
        _counterData.setValue(0);
    }
}
