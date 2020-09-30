package khf.edu.mytools.biz.main.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * 悬浮可拖动按钮
 */
public class FloatButton extends FloatingActionButton {
    private int lastX;
    private int lastY;
    private Scroller mScroller;

    public FloatButton(Context context) {
        super(context);
    }

    public FloatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);

    }

    public FloatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写该方法
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollerTo(int destX, int destY){
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX,0,delta,destY,2000);//在两秒内移动delta像素
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //获取手指触摸点的横坐标和纵坐标
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //方法③,改变布局的参数(不能达到预期效果）
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)getLayoutParams();
//                layoutParams.leftMargin = getLeft()+offsetX;
//                layoutParams.topMargin = getTop()+offsetY;
//                setLayoutParams(layoutParams);
                //还可以用ViewGroup.MarginLayoutParams来实现
//                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                marginLayoutParams.leftMargin = getLeft()+offsetX;
//                marginLayoutParams.topMargin = getTop()+offsetY;
//                setLayoutParams(marginLayoutParams);
                //方法②
                //对left和right进行偏移
                //offsetLeftAndRight(offsetX);
                //对top和bottom 进行偏移
                //offsetTopAndBottom(offsetY);
                //方法①
                //调用layout方法来重新放置它的位置
                layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                break;
        }
        return true;
    }
}
