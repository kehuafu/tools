package khf.edu.mytools.application;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.v4.BuildConfig;

import imageloader.libin.com.images.loader.ImageLoader;
import kehuafu.cn.tools.util.BaseToast;
import kehuafu.cn.tools.util.SPUtils;

public class MyApplication extends Application {
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        BaseToast.init(this);
        SPUtils.init(this);
        ImageLoader.init(this);
        //useStrictMode();

    }

    /**
     * 防止OOM
     *
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.clearAllMemoryCaches();
    }

    /**
     * StrictMode的使用
     */
    private void useStrictMode() {
        if (BuildConfig.DEBUG) {
            //开启线程模式
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            //开启虚拟机模式
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
    }
}
