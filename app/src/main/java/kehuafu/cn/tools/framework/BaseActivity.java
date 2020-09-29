package kehuafu.cn.tools.framework;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 沉浸式开关.
     */
    protected boolean openSteep = false;
    protected int barColor  = Color.WHITE;//默认状态栏为白色

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initListener();
        setLightMode(barColor);
        if (openSteep) {
            steepStatusBar();
        }
    }

    /**
     * 设置状态栏的背景颜色
     * @param barColor
     */
    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }

    /**
     * 隐藏软键盘
     * parentView 需要先设置focusableInTouchMode为true
     *
     * @param parentView 外围被点击的View
     */
    public void hideSoftInput(View parentView) {
        //parentView获取焦点
        parentView.requestFocus();
        View view = this.getWindow().peekDecorView();//用于判断虚拟软键盘是否显示的
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 携带消息跳转
     *
     * @param activity     当前活动
     * @param nextActivity 跳转到的活动
     * @param requestCode  标识的消息
     */
    public void startActivity(Activity activity, Class<? extends Activity> nextActivity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, nextActivity), requestCode);
    }

    /**
     * 不携带消息直接跳转
     *
     * @param activity     当前活动
     * @param nextActivity 跳转到的活动
     */
    public void startActivity(Activity activity, Class<? extends Activity> nextActivity) {
        activity.startActivity(new Intent(activity, nextActivity));
    }

    /**
     * 状态栏设置字体、背景颜色
     */
    private void setLightMode(int barColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            this.getWindow().setStatusBarColor(barColor);
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE |
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (barColor!=Color.WHITE){
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE |
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

    /**
     * 浸入式状态栏实现同时取消5.0以上的阴影
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | layoutParams.flags);
        }
    }

    /**
     * 设置布局，返回布局ID，抽象类，让继承该类的子类去实现
     *
     * @return
     */
    protected abstract int setLayout();

    /**
     * 初始化view，抽象类，让继承该类的子类去实现
     */
    protected abstract void initView();

    /**
     * 初始化监听事件，抽象类，让继承该类的子类去实现
     */
    protected abstract void initListener();

}
