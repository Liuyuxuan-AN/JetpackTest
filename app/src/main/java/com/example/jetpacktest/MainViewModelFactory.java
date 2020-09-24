package com.example.jetpacktest;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * 构建ViewModelProvider.Factory对象，以向ViewModel中传递参数
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {
    private int counter;

    public MainViewModelFactory(int counter){
        this.counter = counter;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(counter);
    }
}
