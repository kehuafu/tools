package kehuafu.cn.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import kehuafu.cn.tools.util.ImageCache;

/**
 * 图片加载类
 */
public class ImageLoader {
    private static final String TAG = "ImageLoader";
    //图片缓存
    ImageCache imageCache = new ImageCache();
    //线程池，线程数量为CPU的数量
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.
            getRuntime().availableProcessors());
    //UI Handler
    Handler mUiHandler = new Handler(Looper.getMainLooper());

    //更新图片
    private void updateImageView(final ImageView imageView, final Bitmap bitmap){
        mUiHandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }
    //加载图片
    public void displayImage(final String url,final ImageView imageView){
        final Bitmap bitmap = imageCache.get(url);
        Log.d(TAG, "displayImage: "+bitmap);
        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                if (bitmap==null){
                    return;
                }
                if (imageView.getTag().equals(url)){
                    updateImageView(imageView,bitmap);
                }
                imageCache.put(url,bitmap);
            }
        });
    }
    //下载图片
    public Bitmap downloadImage(String imageUrl){
        Bitmap bitmap =null;
        try{
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            Log.d(TAG, "downloadImage: "+bitmap);
            conn.disconnect();
        }catch (Exception  e){
            e.printStackTrace();
        }
        return bitmap;
    }
}
