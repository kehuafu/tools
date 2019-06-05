package kehuafu.cn.tools.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 对 SharedPreferences 的简单封装
 * 采用全局的context,在Application 中init，传入全局的context。
 * 调用：SPUtils.Instance().save("xxx",xxx);//保存
 * SPUtils.Instance().get("xxx",xxx);//获取
 */
public class SPUtils {
    private Context context;
    private SharedPreferences sp;
    private static SPUtils spUtils;
    private static String SESSION = "session";
    private String fileName = "data";

    private SPUtils(Context context) {
        this.context = context;
    }

    /**
     * 初始化实例
     *
     * @param context
     * @return
     */
    public static SPUtils init(Context context) {
        if (spUtils == null) {
            synchronized (SPUtils.class) {
                if (spUtils == null) {
                    spUtils = new SPUtils(context);
                }
            }
        }
        return spUtils;
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static SPUtils instance() {
        if (spUtils == null) {
            new RuntimeException("did'nt init before use");
        }
        return spUtils;
    }

    /**
     * 设置存储文件的名称
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取存储文件的名称
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 内部存储方法
     *
     * @param key
     */
    private void saveInside(String key, Object value) {
        sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof Integer){
            editor.putInt(key,(Integer) value);
        }else if (value instanceof Long){
            editor.putLong(key,(Long) value);

        }else if (value instanceof Boolean){
            editor.putBoolean(key,(Boolean) value);
        }else if (value instanceof Float){
            editor.putFloat(key,(Float) value);
        }else if (value instanceof String ){
            editor.putString(key,(String) value);
        }
        editor.apply();
    }

    /**
     * 获取内部存储的值
     * @param key
     * @param value
     * @return
     */
    private Object getInside(String key,Object value){
        sp=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        if (value instanceof Integer){
            return sp.getInt(key,(Integer) value);
        }else if (value instanceof Long){
            return sp.getLong(key,(Long) value);
        }else if (value instanceof Boolean){
            return sp.getBoolean(key,(Boolean) value);
        }else if (value instanceof Float){
            return sp.getFloat(key,(Float) value);
        }else if (value instanceof String ){
            return sp.getString(key,(String) value);
        }else {
            return null;
        }
    }

    /**
     * 对外暴露save方法String型
     * @param key
     * @param value
     */
    public void save(String key,String value){
        saveInside(key,value);
    }

    /**
     * 对外暴露save方法Integer型
     * @param key
     * @param value
     */
    public void save(String key,int value){
        saveInside(key,value);
    }

    /**
     * 对外暴露save方法Boolean型
     * @param key
     * @param value
     */
    public void save(String key,boolean value){
        saveInside(key,value);
    }
    /**
     * 对外暴露save方法Float型
     * @param key
     * @param value
     */
    public void save(String key,float value){
        saveInside(key,value);
    }

    /**
     * 对外暴露save方法Long型
     * @param key
     * @param value
     */
    public void save(String key,long value){
        saveInside(key,value);
    }

    /**
     * 获取存储的String信息
     * @param key
     * @param value
     * @return
     */
    public String get(String key,String value){
        return (String)getInside(key,value);
    }

    /**
     * 获取存储的Integer信息
     * @param key
     * @param value
     * @return
     */
    public Integer get(String key,int value){
        return (Integer)getInside(key,value);
    }

    /**
     * 获取存储的Float信息
     * @param key
     * @param value
     * @return
     */
    public Float get(String key,float value){
        return (Float)getInside(key,value);
    }

    /**
     * 获取存储的Boolean信息
     * @param key
     * @param value
     * @return
     */
    public Boolean get(String key,boolean value){
        return (Boolean)getInside(key,value);
    }

    /**
     * 获取存储的Long信息
     * @param key
     * @param value
     * @return
     */
    public Long get(String key,long value){
        return (Long)getInside(key,value);
    }

    /**
     * 清除所有缓存
     */
    public void clearAll(){
        sp = context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

    /**
     * 清除指定的key键值
     * @param key
     */
    public void clear(String key){
        sp = context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        sp.edit().remove(key).apply();
    }
}
