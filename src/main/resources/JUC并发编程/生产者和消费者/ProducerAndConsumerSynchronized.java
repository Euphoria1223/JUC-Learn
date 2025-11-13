package day01;

public class ProducerAndConsumerSynchronized {
    public static void main(String[] args) {
        Data data = new Data();
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

class Data {
    private int number = 0;

    public synchronized void decrease() throws InterruptedException {
        //如果number==0，则等待
        while (number == 0) {//将if换成while，避免虚假唤醒，即使被虚假唤醒，也会再次检查条件
            wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "->" + number);
        notifyAll();
    }

    public synchronized void increase() throws InterruptedException {
        //如果不等于0，则等待
        while (number != 0) {//将if换成while，避免虚假唤醒，即使被虚假唤醒，也会再次检查条件
            wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "->" + number);
        notifyAll();
    }


}
