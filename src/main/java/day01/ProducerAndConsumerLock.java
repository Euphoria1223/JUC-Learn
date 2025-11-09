package day01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumerLock {
    public static void main(String[] args) {
        Data1 data = new Data1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increase();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrease();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increase();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "c").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrease();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "d").start();

    }
}

class Data1 {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void decrease() throws InterruptedException {
        lock.lock();
        try {
            //如果number==0，则等待
            while (number == 0) {//将if换成while，避免虚假唤醒，即使被虚假唤醒，也会再次检查条件
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "->" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void increase() throws InterruptedException {
        lock.lock();
        try {
            //如果不等于0，则等待
            while (number != 0) {//将if换成while，避免虚假唤醒，即使被虚假唤醒，也会再次检查条件
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "->" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }


}
