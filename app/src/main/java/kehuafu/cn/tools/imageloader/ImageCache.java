package kehuafu.cn.tools.imageloader;

import android.graphics.Bitmap;

public interface ImageCache {
    Bitmap get(String url);
    void put(String url,Bitmap bitmap);
}
