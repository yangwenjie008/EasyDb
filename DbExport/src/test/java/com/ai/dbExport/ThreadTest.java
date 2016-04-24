package com.ai.dbExport;

/**
 * Created by Aaron on 2016/4/24.
 */
public class ThreadTest implements Runnable{

    public void run() {
        System.out.println(Thread.currentThread().getName()+" ---> this is example");
        exec();
    }

    public void exec(){
        for(int i = 0 ; i < 10 ; i++){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+" ----> 开始");
        ThreadTest t1 = new ThreadTest();
        Thread tt1 = new Thread(t1);
        tt1.start();
        System.out.println(Thread.currentThread().getName()+" ----> 结束");
    }
}
