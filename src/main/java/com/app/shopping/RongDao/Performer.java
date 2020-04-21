package com.app.shopping.RongMapper;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Performer implements Runnable {

    private volatile int[] as;
    private volatile int[] newAs;
    private Object lock;
    private AtomicInteger atomicInteger;

    public Performer(int[] as, int[] newAs, Object lock, AtomicInteger ac) {
        this.as = as;
        this.newAs = newAs;
        this.lock = lock;
        this.atomicInteger = ac;
    }

    @Override
    public void run() {
        while (true) {
            if (atomicInteger.get()==16){
                System.out.println("over!!!");
                break;
            }
            ThreadLocalRandom current = ThreadLocalRandom.current();
            int i = current.nextInt(1, 17);//处理值
            System.out.println("产生随机数：" + i);
            synchronized (lock) {
                try {
                    if (as[i - 1] == 0) {
                        continue;
                    }
                    as[i - 1] = 0;
                    if (newAs[i - 1] == 0){
                        newAs[i - 1] = i;
                        atomicInteger.incrementAndGet();
                    }
//                    Thread.sleep(500);
                    System.out.println("线程处理下边坡:" + (i - 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }
    }

    private Integer getNum() {
        Random current = new Random();
        int i = 0;
        while (true) {
            if ((i = current.nextInt(1)) < 16)
                break;
        }
        return i;
    }

    public static void main(String[] args) throws InterruptedException {
        Random current = new Random();
        int i = 0;
        while (true) {
            i = current.nextInt(17);
            System.out.println(i);

        }

    }

}
