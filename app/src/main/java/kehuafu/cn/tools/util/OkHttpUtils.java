package kehuafu.cn.tools.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 对OkHttp3使用的简单封装
 */
@SuppressLint("StaticFieldLeak")
public class OkHttpUtils {

    private static volatile OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private Context mContext;

    public static OkHttpUtils getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(context);
                }
            }
        }
        return mInstance;
    }
    private OkHttpUtils(Context context){
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(),cacheSize));
        mOkHttpClient = builder.build();
        mHandler = new Handler();
        mContext = context;
    }

    /**
     * 异步post请求
     * @param url
     * @param requestBody 表单参数
     * @param callback
     */
    public void postAsyncHttp(String url, RequestBody requestBody, ResultCallback callback){
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call ,callback);
    }

    /**
     * 异步get请求
     * @param url
     * @param callback
     */
    public void getAsyncHttp(String url, ResultCallback callback){
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call ,callback);
    }

    /**
     * 处理结果
     * @param call
     * @param callback
     */
    private void dealResult(Call call, final ResultCallback callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallback(call.request(),e,callback);
            }

            private void sendFailedCallback(final Request request, final IOException e, final ResultCallback callback) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback!=null){
                            callback.onError(request,e);
                        }
                    }
                });
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccessCallback(response.body().string(),callback);
            }

            private void sendSuccessCallback(final String str, final ResultCallback callback) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback!=null){
                            try{
                                callback.onResponse(str);
                            }catch (IOException e){
                                Toast.makeText(mContext,e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}