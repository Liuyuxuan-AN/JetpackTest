package com.example.jetpacktest;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 实现LifecycleObserver接口，在其中实现监听活动生命过程的逻辑
 */
public class MyObserver implements LifecycleObserver {
    private Lifecycle lifecycle;

    public MyObserver(Lifecycle lifecycle){
        this.lifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void activityStart(){
        Log.d("MyObserver","activityStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void activityStop(){
        Log.d("MyObserver","activityStop");
    }
}
