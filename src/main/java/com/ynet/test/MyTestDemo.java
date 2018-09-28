package com.ynet.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kuzi on 2017/11/30.
 */
public class MyTestDemo {
    public static void main(String[] args) throws ParseException {
//        DemoThread d1 = new DemoThread("A");
//        DemoThread d2 = new DemoThread("B");
//        d1.start();
//        d2.start();
//        DemoRunnable dr1 = new DemoRunnable("A");
//        DemoRunnable dr2 = new DemoRunnable("B");
//        Thread t1 = new Thread(dr1);
//        Thread t2 = new Thread(dr2);
//        t1.start();
//        t2.start();
//        Map m = new HashMap();
//        m.put("a","1");
//        m.put("b","2");
//        m.put("c","3");
//        m.put("d","4");
//        m.put("e","5");
//        m.put("f","6");
//        m.put("g","7");
//        m.put("h","8");
//        Iterator i = m.entrySet().iterator();
//        while(i.hasNext()){
//            Map.Entry e = (Map.Entry)i.next();
//            System.out.println(e.getKey()+":"+e.getValue());
//        }
//        int[] arr = {5,9,1,2,6,12,58,23,4};
//        System.out.println(arr.length);
//        for (int i = 0; i <arr.length-1 ; i++) {
//            for (int j = 0; j <arr.length-i-1 ; j++) {
//                if(arr[j]>arr[j+1]){
//                    int a = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = a;
//                }
//            }
//
//        }
//        System.out.println("--------");
//        for (int i:arr
//             ) {
//            System.out.println(i+"---");
//        }

//        LinkedList<Integer> l1 = new LinkedList<Integer>();
//        LinkedList<Integer> l2 = new LinkedList<Integer>();
//        l1.add(1);
//        l1.add(2);
//        l1.add(3);
//        Iterator it = l1.iterator();
//        while(it.hasNext()){
//            l2.addFirst((Integer) it.next());
//        }
//        l1 = l2 ;
//        for (Integer i:
//             l1) {
//            System.out.println(i);
//        }

//        int a,b,c;
//        for (a = 1; a <2018 ; a++) {
//            for (b = 1; b <13 ; b++) {
//                for (c = 1; c < 32 ; c++) {
//                    if(a*b*c == 428575){
//                        System.out.println(a+"-"+b+"-"+c);
//                    }
//                }
//            }
//        }
//        String fundCodeAndFundNameList = "0001|中加货币A,,,0002|中加货币B";
//
//        String[] fundCodeAndFundNames = new String[10];
//        fundCodeAndFundNames = fundCodeAndFundNameList.split(",");
//        for (int i = 0; i <fundCodeAndFundNames.length ; i++) {
//            String fundCodeAndFundName = fundCodeAndFundNames[i];
//            String[] s = fundCodeAndFundName.split("\\|");
//            for (int j = 0; j <s.length ; j++) {
//                System.out.println(s[j]);
//            }
//
//        }
//        String s = "20171227";
//        String s1 = "20171227";
//        if(new SimpleDateFormat("yyyyMMdd").parse(s).after(new SimpleDateFormat("yyyyMMdd").parse(s1))){
//            System.out.println("1111");
//        }

        if(isLastDayOfMonth(new SimpleDateFormat("yyyyMMdd").parse("20171231"))){
            System.out.println("11111");
        }


    }
    private static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, (calendar.get(Calendar.DATE) + 2));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }
}
