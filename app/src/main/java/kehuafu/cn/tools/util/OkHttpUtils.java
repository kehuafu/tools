package kehuafu.cn.tools.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 对OkHttp3使用的简单封装
 */
@SuppressLint("StaticFieldLeak")
public class OkHttpUtils<T> {

    private static final String TAG = "OkHttpUtils";
    private static volatile OkHttpUtils mInstance;
    private static final MediaType JSON_TYPE = MediaType.parse("application/json;charset=utf-8");
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

    private OkHttpUtils(Context context) {
        File sdCache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdCache.getAbsoluteFile(), cacheSize));
        mOkHttpClient = builder.build();
        mHandler = new Handler();
        mContext = context;
    }

    /**
     * 异步post请求
     * 请求参数为泛型<T>
     * @param url
     * @param model
     * @param jSessionId
     * @param callback
     */
    public void postAsyncHttp(String url, T model, String jSessionId, ResultCallback callback) {
        Gson gson = new Gson();
        final Request request = new Request.Builder()
                .header("Cookie", jSessionId)
                .url(url)
                .post(RequestBody.create(JSON_TYPE, gson.toJson(model)))
                .build();
        Call call = mOkHttpClient.newCall(request);
        Log.d(TAG, "postAsyncHttp: "+ gson.toJson(model));
        dealResult(call, callback);
    }

    /**
     * 异步post请求
     * 请求参数为泛型<T>
     * @param url
     * @param model
     * @param callback
     */
    public void postAsyncHttp(String url, T  model, ResultCallback callback) {
        postAsyncHttp(url, model, "", callback);
    }
    /**
     * 异步get请求
     *
     * @param url
     * @param callback
     */
    public void getAsyncHttp(String url, ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call, callback);
    }

    /**
     * 处理结果
     *
     * @param call
     * @param callback
     */
    private void dealResult(Call call, final ResultCallback callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallback(call.request(), e, callback);
            }

            private void sendFailedCallback(final Request request, final IOException e, final ResultCallback callback) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onError(request, e);
                        }
                    }
                });
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccessCallback(response.body().string(), callback);
            }

            private void sendSuccessCallback(final String str, final ResultCallback callback) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            try {
                                callback.onResponse(str);
                            } catch (IOException e) {
                                Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}
