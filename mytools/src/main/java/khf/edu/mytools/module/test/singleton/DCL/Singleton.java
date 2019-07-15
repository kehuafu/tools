package khf.edu.mytools.module.test.singleton.DCL;

//DoubleCheckLock单例模式
public class Singleton {
    private volatile static Singleton mInstance = null;
    private Singleton(){}
    public void doSomething(){
        System.out.println("do sth.");
    }
    public static Singleton getInstance(){
        if (mInstance==null){
            synchronized (Singleton.class){
                if (mInstance==null){
                    mInstance = new Singleton();
                }
            }
        }
        return mInstance;
    }
}
