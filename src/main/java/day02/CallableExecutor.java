package day02;

import java.util.concurrent.*;
//在这个示例中：
//
//我们首先创建了一个固定大小为 2 的线程池 executorService。
//然后定义了一个 Callable 任务，该任务会睡眠 2 秒后返回值 42。
//使用 executorService.submit(callableTask) 方法提交任务，并返回一个 Future 对象。
//通过 future.get() 方法获取任务的执行结果。
//最后在 finally 块中关闭线程池。

public class CallableExecutor {
    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 创建 Callable 任务
        Callable<Integer> callableTask = () -> {
            // 模拟任务执行
            Thread.sleep(2000);
            return 42;
        };

        try {
            // 提交 Callable 任务并获取 Future 对象
            Future<Integer> future = executorService.submit(callableTask);

            // 获取任务执行结果
            Integer result = future.get();
            System.out.println("任务执行结果: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
    }
}