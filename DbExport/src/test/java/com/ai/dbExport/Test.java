package com.ai.dbExport;

/**
 * Created by Aaron on 2016/4/25.
 */
public class Test {
    public void test(){
        ThreadTest test = new ThreadTest();
        Thread thread = new Thread(test);
        thread.start();
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+" ----> 开始");
        Test test = new Test();
        test.test();
        System.out.println(Thread.currentThread().getName()+" ----> 结束");
    }
}
