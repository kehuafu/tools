package khf.edu.mytools.common.util;

import android.content.Intent;
import android.view.View;

import khf.edu.mytools.biz.cardslip.SwipeFinishActivity;
import khf.edu.mytools.biz.waterfall.WaterFallActivity;

/**
 * 跳转到下一个活动工具类
 */
public class GoToNextPageUtil {

    private GoToNextPageUtil() {
    }

    private static class GoToNextPageUtilHolder {
        private static GoToNextPageUtil INSTANCE = new GoToNextPageUtil();
    }

    public static GoToNextPageUtil getInstance() {
        return GoToNextPageUtilHolder.INSTANCE;
    }

    public void goToActivity(View view, int tag) {
        switch (tag) {
            case 0:
                view.getContext().startActivity(new Intent(view.getContext(), WaterFallActivity.class));
                break;
            case 1:
                view.getContext().startActivity(new Intent(view.getContext(), SwipeFinishActivity.class));
                break;
            default:
                break;
        }
    }
}
