package day02;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 */
public class Lock8Demo3 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        Phone1 phone1 = new Phone1();
        //锁的存在
        new Thread(phone::sendSms, "A").start();
        // 捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(phone1::call, "B").start();
    }
}

class Phone2 {
    // synchronized 锁的对象是方法的调用者！
    // 两个方法用的是同一个锁，谁先拿到谁执行！
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

    public void hello(){
        System.out.println("hello");
    }
}
