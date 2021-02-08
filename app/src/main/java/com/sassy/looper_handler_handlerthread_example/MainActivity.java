package com.sassy.looper_handler_handlerthread_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuInflater;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SimpleWorker worker;
    private Worker_using_HandlerThread worker1;
    private TextView textViewMsg;
    //for communicating with main thread we need Handler so that we can update the textview
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            textViewMsg.setText((String)msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMsg = findViewById(R.id.textview);

        //for case1
        worker = new SimpleWorker(); //thread will start running
        //for case2 - in fewer line of code we can achieve same result as of case1
        //worker1 = new Worker_using_HandlerThread();

        //use worker.execute() for case1 and worker1.execute() for case2
        worker.execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = Message.obtain();
            message.obj = "Task 1 completed!";
            handler.sendMessage(message);
        }).execute(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = Message.obtain();
            message.obj = "Task 2 completed!";
            handler.sendMessage(message);
        }).execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = Message.obtain();
            message.obj = "Task 3 completed!";
            handler.sendMessage(message);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        worker.quit();
    }
}
