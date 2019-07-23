package khf.edu.mytools.module.handler.singleton;

import android.content.Context;

/**
 * Context的正确使用
 */
public class SingleInstance {

    private Context mContext;
    private static SingleInstance instance;

    private SingleInstance(Context context) {
        mContext = context;
    }

    public static SingleInstance getInstance(Context context) {
        if (instance == null) {
            instance = new SingleInstance(context.getApplicationContext());//这一句是关键，使用全局的context
        }
        return instance;
    }
}
