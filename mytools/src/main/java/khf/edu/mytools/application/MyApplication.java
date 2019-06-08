package khf.edu.mytools.application;

import android.app.Application;
import android.content.Context;

import kehuafu.cn.tools.util.BaseToast;
import kehuafu.cn.tools.util.SPUtils;

public class MyApplication extends Application {
    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        BaseToast.init(this);
        SPUtils.init(this);
    }
}
