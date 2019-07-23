package khf.edu.mytools.module.test.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JVM
 */
public class SynchronizedExample {

    /**
     * 1、同步一个代码快，同步语句块
     */
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("" + i);
            }
        }
    }

    /**
     * 2、同步一个方法，与同步语句块一样，作用于同一个对象
     */
    public synchronized void func2() {
        for (int i = 0; i < 10; i++) {
            System.out.println("" + i);
        }
    }

    /**
     * 3、同步一个类,也就是说两个线程调用一个类的不同对象上的这种同步语句，也会进行同步
     */
    public void func3() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println("" + i);
            }
        }
    }

    /**
     * 同步一个静态方法，作用于整个类
     */
    public synchronized static void func4() {
        for (int i = 0; i < 10; i++) {
            System.out.println("" + i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.func4());
        executorService.execute(() -> e2.func4());//Lambda表达式
    }
}
