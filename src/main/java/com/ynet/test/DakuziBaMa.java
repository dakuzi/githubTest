package com.ynet.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by kuzi on 2018/1/14.
 */
public class DakuziBaMa implements InvocationHandler {

    People people;

    public DakuziBaMa(People people){
        this.people = people;
    };

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //代理方法，增强方法，对dakuzi方法的代理
        before();
        people.zhaoduixiang();
        //代理方法，增强方法，对dakuzi方法的代理
        after();

        return null;
    }
    private void before(){
        System.out.println("我是大酷子爸妈，在他之前帮他找对象！！！");
    }
    private void after(){
        System.out.println("我是大酷子爸妈，在他之后帮他带小孩！！！");
    }
}
