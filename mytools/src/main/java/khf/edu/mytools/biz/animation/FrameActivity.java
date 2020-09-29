package khf.edu.mytools.biz.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import kehuafu.cn.tools.custom.RoundImageView;
import kehuafu.cn.tools.framework.BaseActivity;
import khf.edu.mytools.R;

public class FrameActivity extends BaseActivity implements View.OnClickListener {
    private Button btnClick;
    private ImageView frameIv;
    private Button tweenClick;
    private RoundImageView tweenIv;

    @Override
    protected int setLayout() {
        return R.layout.frame_activity;
    }

    @Override
    protected void initView() {
        btnClick = findViewById(R.id.btn_click);
        frameIv = findViewById(R.id.frame_iv);
        tweenClick = findViewById(R.id.tween_click);
        tweenIv = findViewById(R.id.tween_iv);
        //属性动画,头像
        Animator animator = ObjectAnimator.ofFloat(tweenIv,"rotation", 0F, 30F);
        animator.setDuration(2000);//动画时长
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    @Override
    protected void initListener() {
        btnClick.setOnClickListener(this);
        tweenClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
//                frameIv.setImageResource(R.drawable.frame);
//                AnimationDrawable animationDrawable = (AnimationDrawable) frameIv.getDrawable();
//                animationDrawable.start();
                break;
            case R.id.tween_click:
                //补间动画
//                tweenIv.setImageResource(R.drawable.koala);
//                AlphaAnimation animation = new AlphaAnimation(0,1);
//                animation.setDuration(1000);
//                animation.setFillAfter(true);//动画结束后保留结束状态
//                tweenIv.setAnimation(animation);

//                tweenIv.setImageResource(R.drawable.wave);
//                RotateAnimation animation = new RotateAnimation(0, -10,
//                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//                animation.setDuration(10000);
//                tweenIv.setAnimation(animation);

//                //属性动画
//                Animator animator = AnimatorInflater.loadAnimator(this,R.animator.scale);
//                animator.setTarget(tweenIv);
//                animator.start();

                Animator animator = ObjectAnimator.ofFloat(tweenIv,"rotation", 0F, 30F);
                animator.setDuration(2000);//动画时长
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();
                break;
        }
    }
}
