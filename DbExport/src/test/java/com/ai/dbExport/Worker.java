package com.ai.dbExport;

/**
 * Created by Aaron on 2016/4/24.
 */
public class Worker{
    private volatile boolean done;

    public void setDone(boolean done) {
        System.out.println(Thread.currentThread().getName());
        while (!done) {
            System.out.println("88888888888888888888888");
        }
        this.done = done;
    }

    public void work() {
        System.out.println(Thread.currentThread().getName());
        while (!done) {
            System.out.println("this is hello world!!!");
        }
    }


    public static void main(String[] args) throws InterruptedException {

    }
}
