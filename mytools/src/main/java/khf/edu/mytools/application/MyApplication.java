package khf.edu.mytools.application;

import android.app.Application;
import android.content.Context;

import kehuafu.cn.tools.util.BaseToast;

public class MyApplication extends Application {
    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        BaseToast.init(context);
    }
}
