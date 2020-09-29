
package khf.edu.mytools.module.handler.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.lang.ref.WeakReference;

import khf.edu.mytools.R;
import khf.edu.mytools.module.home.activity.MainActivity;

public class HandlerActivity extends AppCompatActivity {
    private static Context context;

    /**
     * 声明一个静态的Handler内部类，并持有外部类的弱引用
     */
    private static class InnerHandler extends Handler {
        private final WeakReference<HandlerActivity> mActivity;

        public InnerHandler(HandlerActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivity activity = mActivity.get();
            if (activity != null) {
                //...
            }
        }
    }

    private final InnerHandler handler = new InnerHandler(this);

    /**
     * 静态的匿名内部类不会持有外部类的引用
     */
    private static final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(context,"8888888888",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        context = this;
        //延迟10秒发送消息
        handler.postDelayed(runnable, 1000 * 10 );

        //获取资源管理器对象，进而可以访问到例如：string、color等资源
        Resources resources = context.getResources();
        //启动指定的Activity
        context.startActivity(new Intent(this, MainActivity.class));
        //获取各种系统服务
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        //获取系统文件目录
        File internaDir = context.getCacheDir();
        File externaDir = context.getExternalCacheDir();
        //更多...
    }
}
