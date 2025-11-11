package day02;

import java.util.concurrent.CountDownLatch;

// 计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
// 总数是6，必须要执行任务的时候，再使用！
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Go out");
                countDownLatch.countDown(); // 数量-1
            }, String.valueOf(i)).start();
        }
        countDownLatch.await(); // 等待计数器归零，然后再向下执行  ，去掉await的话，close door可能会在线程执行完之前就输出了
        System.out.println("Close Door");
    }
}