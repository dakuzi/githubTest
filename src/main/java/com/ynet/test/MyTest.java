package com.ynet.test;


import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * Created by kuzi on 2018/1/14.
 */
public class MyTest {
    public static void main(String[] args) {
        //返回一个代理类
        People people = (People)Proxy.newProxyInstance(People.class.getClassLoader(), new Class[]{People.class}, new DakuziBaMa(new Dakuzi()));
        people.zhaoduixiang();
        createProxyClassFile();
    }

    public static void createProxyClassFile(){
        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0.class",new Class[]{People.class});
        try {
            FileOutputStream out = new FileOutputStream("$Proxy0.class");
            out.write(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
