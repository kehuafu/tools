package khf.edu.mytools.module.test.threadUnsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程不安全示例：
 * 如果多个线程对同一个共享数据进行访问而不采取同步操作的话，那么操作的结果是不一致的！！
 * 例如：1000个线程对cnt执行自增操作，操作结束之后它的值有可能小于1000。
 */
public class ThreadUnsafeExample {
    private int cnt = 0;

    /**
     * 若加synchronized对其进行同步，这样操作结束后的值就是1000.
     */
    public synchronized void add() {
        cnt++;
    }

    public int get() {
        return cnt;
    }

    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }
}
