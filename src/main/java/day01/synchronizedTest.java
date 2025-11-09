package day01;

public class synchronizedTest {
    public static void main(String[] args) {
        ticket ticket = new ticket();
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
    }
}

class ticket {
    private int ticketNum = 10;

    public synchronized void sale() {
        if (ticketNum > 0) {
            System.out.println(Thread.currentThread().getName() + ": 卖票，票号为：" + ticketNum);
            ticketNum--;
        }
    }
}
