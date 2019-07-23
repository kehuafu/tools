package khf.edu.mytools.module.test.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程之间的协作
 */
public class WaitNotifyExample {

    //同步方法,before
    public synchronized void before() {
        System.out.println("before");
        notifyAll();//唤醒after
    }

    //同步方法,after
    public synchronized void after() {
        try {
            wait();//当前线程会被挂起，并且释放锁，等待notify()或者notifyAll()唤醒。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitNotifyExample example = new WaitNotifyExample();
        executorService.execute(()->example.after());
        executorService.execute(()->example.before());
        executorService.shutdown();

    }
}
