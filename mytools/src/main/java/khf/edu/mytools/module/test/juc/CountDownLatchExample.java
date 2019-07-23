package khf.edu.mytools.module.test.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个线程等待多个线程
 */
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        final int totalThread = 18;
        CountDownLatch cnt = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.println("run...");
                cnt.countDown();
            });
        }
        cnt.await();//若cnt不等于0，该线程一直被挂起，直到cnt=0时，该线程才会被唤起。一个线程等待多个线程
        System.out.println("end");
        executorService.shutdown();
    }
}
