package khf.edu.mytools.module.test.cooperation;

/**
 * 线程之间的协作
 */
public class JoinExample {

    public static class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    public static class B extends Thread {
        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

    /**
     * 虽然 b 线程先启动，但是因为在 b 线程中调用了a线程的join()方法，b 线程会等待 a 线程执行完毕后才继续执行。
     * @param args
     */
    public static  void main(String [] args){
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }
}
