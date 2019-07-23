package khf.edu.mytools.module.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口，可以有返回值，返回值通过FutureTask进行封装
 */
public class MyCallable implements Callable<List<String>> {
    @Override
    public List<String> call() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }
        return list;
    }

    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        FutureTask<List<String>> ft = new FutureTask<>(myCallable);
        Thread thread = new Thread(ft);
        thread.start();
        try {
            for (String s : ft.get()) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
