package day03;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;



/**
 * @author AlbertEu
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        /*
         * 同步队列
         * 和其他的BlockingQueue 不一样， SynchronousQueue 不存储元素
         * put了一个元素，必须从里面先take取出来，否则不能在put进去值！会直接阻塞住
         */

        BlockingQueue<String> queue = new SynchronousQueue<>();

        // 生产者线程
        new Thread(() -> {
            try {
                System.out.println("生产者准备放入数据...");
                queue.put("ss");
                System.out.println("生产者成功放入数据");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 消费者线程
        new Thread(() -> {
            try {
                Thread.sleep(1000); // 稍等一会儿让生产者先卡住
                System.out.println("消费者准备取数据...");
                String data = queue.take();
                System.out.println("消费者取到数据：" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
