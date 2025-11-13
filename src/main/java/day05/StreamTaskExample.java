package day05;

import java.util.stream.LongStream;

/**
 * @author ----- AlbertEu
 * @since ----- 2025/11/13
 * description:
 */
public class StreamTaskExample {
    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        // Stream并行流
        //long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().sum();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();

        System.out.println("sum=" + sum + "，时间：" + (end - start));
    }

}
