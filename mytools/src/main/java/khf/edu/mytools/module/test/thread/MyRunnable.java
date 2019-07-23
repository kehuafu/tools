package khf.edu.mytools.module.test.thread;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 实现Runnable接口
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        //获取网络字节流，并将该字节流转换成图片.jpg的格式
        try {
            URL url = new URL("http://www.huaguangstore.com.cn/user_images/yuge.jpg");
            BufferedInputStream buff = new BufferedInputStream(url.openConnection().getInputStream());
            FileOutputStream file = new FileOutputStream("D://yu.jpg", false);
            BufferedOutputStream bout = new BufferedOutputStream(file);
            byte[] bytes = new byte[50 * 1024];
            int len;
            while ((len = buff.read(bytes)) != -1) {
                bout.write(bytes, 0, len);
            }
            System.out.println("完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class MyRunnableNext implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                for (int i = 0; ; i ++) {
                    System.out.println("" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        MyRunnable  myRunnable= new MyRunnable();
//        Thread thread = new Thread(myRunnable);
//        thread.start();

        //Executor
        ExecutorService executorService = Executors.newCachedThreadPool();//一个任务创建一个线程
//        executorService.execute(new MyRunnable());
//        executorService.execute(new MyRunnableNext());
//        executorService.shutdown();
        //使用Lambda创建线程，相当于创建一个匿名内部线程
        executorService.execute(()->{
            try {
                Thread.sleep(2000);
                for (int i = 0;i<10 ; i ++) {
                    System.out.println("" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        System.out.println("Main Run");
    }
}
