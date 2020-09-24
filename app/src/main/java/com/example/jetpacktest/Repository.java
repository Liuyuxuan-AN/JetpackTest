package com.example.jetpacktest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Repository {
    private static Repository instance;

    private Repository(){};

    public synchronized static Repository getInstance() {
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public LiveData<User> getUser(String userId){
        MutableLiveData<User> userData = new MutableLiveData<>();
        userData.setValue(new User(userId,userId,20));
        return userData;
    }
}
