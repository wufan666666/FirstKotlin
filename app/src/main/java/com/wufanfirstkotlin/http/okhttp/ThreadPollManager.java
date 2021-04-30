package com.wufanfirstkotlin.http.okhttp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : wf
 * @date : 2021年04月29日 15:13
 */
public class ThreadPollManager {

    private static ThreadPollManager threadPollManager = new ThreadPollManager();


    public static ThreadPollManager getInstance(){
        return threadPollManager;
    }



    private LinkedBlockingDeque<Runnable> mQueue = new LinkedBlockingDeque<>();

    public void addTask(Runnable runnable){
        try {
            mQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private ThreadPoolExecutor threadPoolExecutor;
    private ThreadPollManager(){
        threadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4)
                , new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                addTask(r);
            }
        });
        threadPoolExecutor.execute(coreThread);
    }
    public Runnable coreThread= new Runnable() {
        Runnable run = null;
        @Override
        public void run() {
            while (true){
                try {
                    run = mQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPoolExecutor.execute(run);
            }
        }
    };

}