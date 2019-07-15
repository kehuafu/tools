package kehuafu.cn.tools.imageloader;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * 双缓存DoubleCache类
 */
public class DoubleCache implements ImageCache {
    private static final String TAG = "DoubleCache";
    private ImageCache mMemoryCache = new MemoryCache();
    private ImageCache mDiskCache = new DiskCache();

    //先从内存缓存中获取图片，如果没有，再从SD卡中获取
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap==null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    //将图片缓存到内存和SD卡中
    @Override
    public void put(String url, Bitmap bitmap) {
        Log.d(TAG, "put: "+url);
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }
}
