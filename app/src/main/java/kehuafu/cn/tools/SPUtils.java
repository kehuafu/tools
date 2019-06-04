package kehuafu.cn.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 工具类：SharedPreferences
 */
public class SPUtils {

    private static SharedPreferences sp = null;
    public static String SESSION = "session";

    private static void getSP(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        }
    }

    public static void putString(Context context, String key, String value) {
        getSP(context);
        sp.edit().putString(key, value).apply();
    }
    public static void putInt(Context context, String key, int value) {
        getSP(context);
        sp.edit().putInt(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        getSP(context);
        return sp.getString(key, defValue);
    }
    public static int getInt(Context context, String key, int defValue) {
        getSP(context);
        return sp.getInt(key, defValue);
    }

    @SuppressLint("ApplySharedPref")
    public static void clearData(Context context){
        getSP(context);
        sp.edit().clear().commit();
    }
}
