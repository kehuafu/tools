package khf.edu.mytools.application;

import android.app.Application;
import android.content.Context;

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
    }

    /**
     * 防止OOM
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
}
