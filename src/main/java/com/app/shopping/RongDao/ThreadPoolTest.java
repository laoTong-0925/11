package com.app.shopping.RongDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {
    private int[] array;
    private int[] newArray;
    private static volatile ThreadPoolTest single;

    public static ThreadPoolTest getInstance() {
        if (null == single) {
            synchronized (ThreadPoolTest.class) {
                if (null == single) {
                    single = new ThreadPoolTest();
                }
            }
        }
        return single;
    }

    public void creatArray(ThreadPoolTest sin, int num) {
        array = new int[num];
        newArray = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = i + 1;
            newArray[i] = 0;
        }
    }

    public static void main(String[] args) {
        ThreadPoolTest instance = ThreadPoolTest.getInstance();
        instance.creatArray(instance, 16);
//        for (Integer i :
//                instance.array) {
//            System.out.println(i);
//        }
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Object lock = new Object();
        AtomicInteger ac = new AtomicInteger();
        executorService.submit(new Performer(instance.array, instance.newArray, lock, ac));
        executorService.submit(new Performer(instance.array, instance.newArray, lock, ac));
        executorService.submit(new Performer(instance.array, instance.newArray, lock, ac));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 16; i++) {
            System.out.println(instance.newArray[i]);
        }
        executorService.shutdown();
    }

}
