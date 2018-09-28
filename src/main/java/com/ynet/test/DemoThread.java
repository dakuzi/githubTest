package com.ynet.test;

/**
 * Created by kuzi on 2017/11/30.
 */
public class DemoThread extends Thread {

    private String name;

    public DemoThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println(name+":"+i);
        }
    }
}
