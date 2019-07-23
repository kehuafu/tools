package kehuafu.cn.tools.imageloader;

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

/**
 * 图片加载类，添加Builder模式
 */
public final class ImageLoader {
    private static final String TAG = "ImageLoader";
    //ImageLoader实例
    private static ImageLoader mInstance;
    //图片缓存
    ImageCache imageCache = new MemoryCache();
    //线程池，线程数量为CPU的数量
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.
            getRuntime().availableProcessors());
    //UI Handler
    Handler mUiHandler = new Handler(Looper.getMainLooper());

    //获取ImageLoader单例、DCL形式
    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    //更新图片
    private void updateImageView(final ImageView imageView, final Bitmap bitmap) {
        mUiHandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    //注入缓存实现
    public void setImageCache(ImageCache cache) {
        imageCache = cache;
    }

    //加载图片
    public void displayImage(String url, ImageView imageView) {
        Bitmap bitmap = imageCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        //图片没有缓存，提交到线程池中下载图片
        submitLoaderRequest(url, imageView);
    }

    private void submitLoaderRequest(final String url, final ImageView imageView) {
        imageView.setTag(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    updateImageView(imageView, bitmap);
                }
                Log.d(TAG, "run: " + url);
                imageCache.put(url, bitmap);
            }
        });
    }

    //下载图片
    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "downloadImage: " + bitmap);
        return bitmap;
    }

    /**
     * 图片加载Listener，加载完成后回调给客户端代码
     */
    public interface ImageListener {
        void onComplete(ImageView imageView, Bitmap bitmap, String url);
    }
}
