package khf.edu.mytools.module.test.singleton.hungry;


//CEO，饿汉单例模式
public class CEO  extends Staff{

    private static final CEO mCEO = new CEO();
    private CEO(){
    }
    public static CEO getmCEO(){
        return mCEO;
    }
}
