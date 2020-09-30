package khf.edu.mytools.biz.cardslip;

import android.widget.RadioGroup;
import kehuafu.cn.tools.framework.BaseActivity;
import khf.edu.mytools.R;
import khf.edu.mytools.common.helper.BindView;
import khf.edu.mytools.common.helper.SwipeBackLayout;

public class SwipeFinishActivity extends BaseActivity {

    @BindView(R.id.selectDirect)
    private RadioGroup mSelectDirect;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_swipe_finish;
    }

    @Override
    protected void initView() {
        setBarColor(getResources().getColor(R.color.color_b9_dark));
        mSelectDirect = findViewById(R.id.selectDirect);
        mSwipeBackLayout = new SwipeBackLayout(this);
        mSwipeBackLayout.attachToActivity(this);

        mSelectDirect.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.left:
                    mSwipeBackLayout.setDirectionMode(SwipeBackLayout.FROM_LEFT);
                    break;
                case R.id.right:
                    mSwipeBackLayout.setDirectionMode(SwipeBackLayout.FROM_RIGHT);
                    break;
                case R.id.up:
                    mSwipeBackLayout.setDirectionMode(SwipeBackLayout.FROM_TOP);
                    break;
                case R.id.down:
                    mSwipeBackLayout.setDirectionMode(SwipeBackLayout.FROM_BOTTOM);
                    break;
                default:
                    mSwipeBackLayout.setDirectionMode(SwipeBackLayout.FROM_ANY);
                    break;
            }
        });
    }

    @Override
    protected void initListener() {

    }
}
