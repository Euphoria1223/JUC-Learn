package day01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        ticket2 ticket = new ticket2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }, "C").start();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}

class ticket2 {
    private int ticketNum = 10;
    //建锁放在外面，保住只有一把锁，放在方法里会导致每次执行方法都生成一个新的锁，相当于没加锁
    Lock lock = new ReentrantLock();//参数可以填true，为true时则是公平锁，默认为非公平锁，公平锁讲究先来后到
    public void sale() {
        lock.lock();
        try {
            if (ticketNum > 0) {
                System.out.println(Thread.currentThread().getName() + ": 卖票，票号为：" + ticketNum);
                ticketNum--;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
