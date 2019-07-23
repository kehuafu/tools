package khf.edu.mytools.module.test;

public class Test implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnining");
    }
    public static void main(String[] args){
        Thread t = new Thread( new Test());
        t.start();
    }
}
