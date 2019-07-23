package khf.edu.mytools.module.test.thread;

/**
 * 继承Thread
 */
public class MyThread extends Thread {
    @Override
    public void run() {
       //...
    }
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}

