package kehuafu.cn.tools.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import kehuafu.cn.tools.R;

/**
 * 对Toast 的简单封装
 * 采用的是全局context,所以要在Application 中进行初始化，传入全局的context
 * 可以避免静态的Toast 一直持有Activity 的引用,造成内存泄漏
 */
public class BaseToast {
    private static volatile BaseToast baseToast = null;
    private static Context context;
    private static Toast toast;
    private static TextView tip_tv;
    private static LinearLayout tip_ll;

    public BaseToast(Context context){
        BaseToast.context = context;
        if (toast ==null){
            toast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
        }
    }

    /**
     * 在Application中初始化
     * @param context
     */
    public static void init(Context context){
        if (baseToast==null){
            synchronized (BaseToast.class){
                baseToast = new BaseToast(context);
            }
        }
    }
    private static void show(View view,String msg, int time){
        if (context==null){
            try {
                throw new Exception("Context did'nt init");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (view!=null){
            tip_tv = view.findViewById(R.id.toast_tip);
            tip_ll = view.findViewById(R.id.tip_ll);
            tip_ll.setAlpha(0.8f);
            tip_tv.setText(msg);
            toast.setView(view);
            toast.setGravity(Gravity.BOTTOM,0,200);
            toast.setDuration(time);
            toast.show();
        }else {
            toast.setText(msg);
            toast.setDuration(time);
            toast.show();
        }

    }

    /**
     * 暴露方法
     * @param view
     * @param msg
     */
    public static void showShort(View view,String msg){
        show(view,msg,Toast.LENGTH_SHORT);
    }
    public static void showLong(View view,String msg){
        show(view,msg,Toast.LENGTH_LONG);
    }
    public static void cancel(){
        toast.cancel();
    }
}
