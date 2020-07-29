package com.example.testapplication;

import androidx.core.util.ObjectsCompat;

public class CrashHelper {

    /**
     * 线程阻塞
     *
     */
    static boolean flag = true;
    static Object lock = new Object();
    public String threadBlockFunc(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    while (flag){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    while (flag){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "threadBlock";
    }

    public String nullPointerFunc() {
        String x = null;
        x.contains("abc");
        return "nullPointer";
    }

    public String cpuOverloadFunc() { // 有点不知道该怎么搞
        memoryLeakFunc();
        return "cpuOverload";
    }

    public String memoryLeakFunc() {
        String s = "abcdefg";
        int i= Integer.MIN_VALUE;
        while(i<Integer.MAX_VALUE){
            s += s;
            i++;
        }
        return "memoryLeak";
    }

    public String exceptionFunc() {
        int i = 1/0;
        return "exception";
    }

    public String errorFunc(){
        while(true)
            errorFunc();
    }

    public String forceCloseFunc() {
        android.os.Process.killProcess(android.os.Process.myPid()); // 自杀
//        System.exit(0);
        return "forceClose";
    }
}
