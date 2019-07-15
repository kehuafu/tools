package khf.edu.mytools.module.test.singleton.lazy;

//懒汉单例模式
public class Singleton {
    //声明一个静态对象
    private static Singleton instance;
    private Singleton(){}

    public static synchronized Singleton getInstance(){
        if (instance==null){
            instance = new Singleton();
        }
        return instance;
    }
}
