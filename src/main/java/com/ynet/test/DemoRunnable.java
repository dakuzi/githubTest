package com.ynet.test;

/**
 * Created by kuzi on 2017/11/30.
 */
public class DemoRunnable implements Runnable {

    private String name;

    public DemoRunnable (String name){
        this.name = name;
    }

    public void run() {

        for (int i = 0; i <50 ; i++) {
            System.out.println(name+":"+i);
        }
    }
}
