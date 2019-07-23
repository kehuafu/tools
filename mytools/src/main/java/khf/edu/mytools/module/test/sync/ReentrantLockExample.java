package khf.edu.mytools.module.test.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JDK
 */
public class ReentrantLockExample {
    private Lock lock = new ReentrantLock();

    public void fun(){
        lock.lock();
        try{
            for (int i = 0; i < 10; i++) {
                System.out.println("" + i);
            }
        }finally {
            lock.unlock();//确保释放锁，从而避免发生死锁。
        }
    }
    public static void main(String[] args) {
        ReentrantLockExample lockExample = new ReentrantLockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->lockExample.fun());
        executorService.execute(()->lockExample.fun());
    }
}
