package khf.edu.mytools.module.test.singleton.container;

import java.util.HashMap;
import java.util.Map;

import khf.edu.mytools.module.test.singleton.enum_.Singleton;

/**
 * 使用容器实现单例模式：在程序的初始，将多种单例类型注入到一个统一管理类中；
 */
public class SingletonManager {
    private static Map<String ,Object> objectMap = new HashMap<>();

    private SingletonManager(){}
    public static void registerService(String key, Object instance){
        if (!objectMap.containsKey(key)){
            objectMap.put(key,instance);
        }
    }
    public static Object getService(String key){
        return objectMap.get(key);
    }
    public static void main(String[] args){
        SingletonManager.registerService("koko", Singleton.INSTANCE);

        Singleton singleton = (Singleton)SingletonManager.getService("koko");
        singleton.doSomething();
    }
}
