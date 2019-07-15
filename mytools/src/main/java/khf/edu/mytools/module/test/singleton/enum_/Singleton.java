package khf.edu.mytools.module.test.singleton.enum_;

/**
 * 枚举单例
 */
public enum Singleton {
    INSTANCE;
    public void doSomething(){
        System.out.println("do sth.。。。。");
    }
    public static void main(String[] args){
        Singleton.INSTANCE.doSomething();
    }
}
