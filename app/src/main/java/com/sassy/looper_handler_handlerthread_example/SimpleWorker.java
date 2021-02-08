package com.sassy.looper_handler_handlerthread_example;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleWorker extends Thread {

    private static final String TAG = "SimpleWorker";
    //for looping
    private AtomicBoolean alive = new AtomicBoolean(true);
    //ConcurrentLinkedQueue allows multiple threads to add task in queue.
    private ConcurrentLinkedQueue<Runnable> tasksQueue = new ConcurrentLinkedQueue<>();

    public SimpleWorker() {
        super(TAG);
        start(); //start() method executes run()
    }

    @Override
    public void run() {
        while (alive.get()) {
            Runnable task = tasksQueue.poll();
            if(task != null){
                task.run();
            }
        }
        Log.i(TAG , "SimpleWorker terminated");
    }


    //As an interface for the outer world we provide a method called execute and
    //return instance of SimpleWorker class so that we can use builder like thing to
    //add more task to it.(Whenever task is enqueued we add it to messageQueue.
    public SimpleWorker execute(Runnable task){
        tasksQueue.add(task);
        return this;
    }

    public void quit(){
        //setting alive to false will break the while loop and thread will terminate
        alive.set(false);
    }

}
