package com.sassy.looper_handler_handlerthread_example;

import android.os.Handler;
import android.os.HandlerThread;

public class Worker_using_HandlerThread extends HandlerThread {

    private static final String TAG = "Worker_using_HandlerThr";

    private Handler handler;

    public Worker_using_HandlerThread() {
        super(TAG);
        start();
        handler = new Handler(getLooper());
    }

    public Worker_using_HandlerThread execute(Runnable task){
        //we need handler to put this Runnable task to our msqQueue
        // which is then supplied to HandlerThread.
        handler.post(task);
        return this;
    }
    
}
