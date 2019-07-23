package khf.edu.mytools.module.test.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 在主线程中直接获取异步数据，返回值采用FutureTask封装。
 */
public class FutureTaskExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Object> futureTask = new FutureTask<>(() -> {
            System.out.println("在子线程执行任务中...");
            int result = 0;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(10);
                result += i;
            }
            return result;
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("主线程："+futureTask.get());
    }
}
