package kehuafu.cn.tools.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * ViewGroup的子类
 */
public class MyRelativeLayout extends RelativeLayout {
    private static final String TAG = "MyRelativeLayout";
    public MyRelativeLayout(Context context) {
        super(context);
    }
    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 分发
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.d(TAG, "dispatchTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                //Log.d(TAG, "dispatchTouchEvent: ACTION_UP");
                    break;
            case MotionEvent.ACTION_CANCEL:
                //Log.d(TAG, "dispatchTouchEvent: ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 拦截
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.d(TAG, "onInterceptTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                //Log.d(TAG, "onInterceptTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                //Log.d(TAG, "onInterceptTouchEvent: ACTION_CANCEL");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 消费
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.d(TAG, "onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                //Log.d(TAG, "onTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                //Log.d(TAG, "onTouchEvent: ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(ev);
    }
    @Override
    public View getRootView() {
        return super.getRootView();
    }
}
