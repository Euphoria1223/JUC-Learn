package day02;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 */
public class Lock8Demo4 {
    public static void main(String[] args) {
        Phone3 phone3 = new Phone3();
        //锁的存在
        new Thread(Phone3::sendSms, "A").start();
        // 捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(phone3::call, "B").start();
        new Thread(phone3::hello,"C").start();
    }
}

class Phone3 {
    // synchronized 锁的对象是方法的调用者！
    // 两个方法用的是同一个锁，谁先拿到谁执行！
    // static+synchronized，锁的是Phone3这个class，类里的一个静态方法拿到锁后，释放前，其他静态锁方法都无法执行，
    // 即使是不同对象，因为他们都用的是同一个class模板也无法执行，不管创建多少个对象，静态方法用的都是同一把锁。
    // 如果是一个静态锁，一个非静态锁，则执行互不干涉
    // 普通方法不影响
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public  synchronized void call() {
        System.out.println("打电话");
    }

    public void hello() {
        System.out.println("hello");
    }
}
