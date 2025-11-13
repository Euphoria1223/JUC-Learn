package day05;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ----- AlbertEu
 * @since ----- 2025/11/13
 * description:
 */
public class SpinLock {

    private AtomicReference<Thread> cas = new AtomicReference<Thread>();

    public void lock() {

        Thread current = Thread.currentThread();

    // 利用CAS

        while (!cas.compareAndSet(null, current)) {

    // DO nothing

        }

    }

    public void unlock() {

        Thread current = Thread.currentThread();

        cas.compareAndSet(current, null);

    }

}
