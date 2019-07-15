package kehuafu.cn.tools.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import kehuafu.cn.tools.util.CloseUtils;
import kehuafu.cn.tools.util.EncryptionUtils;
import okhttp3.internal.cache.DiskLruCache;

/**
 * SD卡缓存类
 */
public class DiskCache implements ImageCache {

    private static final String TAG = "DiskCache";
    static String cacheDir = "sdcard/cache/";

    //从本地文件中获取图片
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);/*从本地文件中获取该图片*/
    }

    //将图片缓存到SD卡中
    @Override
    public void put(String url, Bitmap bitmap) {
        //将Bitmap 写入文件中
        FileOutputStream fileOutputStream = null;
        Log.d(TAG, "put: "+EncryptionUtils.getMD5(url));
        try {
            fileOutputStream = new FileOutputStream("sdcard/cache/"+ url);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(fileOutputStream);
        }
    }
}
