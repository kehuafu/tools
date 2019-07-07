package khf.edu.mytools.module.cardslip.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import khf.edu.mytools.R;
import khf.edu.mytools.common.helper.BindView;
import khf.edu.mytools.common.helper.SwipeBackLayout;

public class SwipeFinishActivity extends AppCompatActivity {

    @BindView(R.id.selectDirect)
    private RadioGroup mSelectDirect;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_finish);
        initView();
    }

    private void initView() {
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
}
