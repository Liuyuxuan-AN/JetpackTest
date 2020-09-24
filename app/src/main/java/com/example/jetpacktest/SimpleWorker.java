package com.example.jetpacktest;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SimpleWorker extends Worker {
    public SimpleWorker(Context context, WorkerParameters workerParameters){
        super(context,workerParameters);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("SimpleWorker","Do work in SimpleWorker");
        return Result.success();
    }
}
