package khf.edu.mytools.module.home.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import imageloader.libin.com.images.loader.ImageLoader;
import kehuafu.cn.tools.framework.BaseActivity;
import kehuafu.cn.tools.util.BaseToast;
import khf.edu.mytools.R;
import khf.edu.mytools.module.home.view.FloatButton;
import khf.edu.mytools.module.leave.bean.LeaveBeanShell;
import khf.edu.mytools.module.leave.dialog.Dialog;
import khf.edu.mytools.module.waterfall.activity.WaterFallActivity;
import okhttp3.MediaType;


public class MainActivity extends BaseActivity {
    private Context context;
    private TextView click_tv;
    private Dialog dialog;
    private static List<LeaveBeanShell> shells;//存储课程数据信息
    private View view;
    private static final String TAG = "MainActivity";
    private static final MediaType JSON_TYPE = MediaType.parse("application/json;charset=utf-8");
    private static final String HEADER_NAME = "Authorization";
    private static final String HEADER_CONTENT = "Bearer";
    private LinearLayout linearLayout;
    private FloatButton fab;
    private Button button;
    private TextView testTv;
    private ImageView cardIv;

    @Override
    protected void initListener() {

        //使用BaseToast示例
        view = getLayoutInflater().inflate(kehuafu.cn.tools.R.layout.toast_tip, null);
        BaseToast.showShort(view, "计算机网络实践教程");

        shells = new ArrayList<>();
        click_tv = findViewById(R.id.click_tv);
        click_tv.setOnClickListener(v -> {
            //自定义对话框使用示例
//            dialog = new Dialog();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("shells", (Serializable) shells);
//            dialog.setArguments(bundle);
//            dialog.show(getSupportFragmentManager(), "dialog");

            //异步post请求示例
//                Map<String, Object> json = new HashMap<>();
//                Gson gson = new Gson();
//                json.put("studentID", "");
//                RequestBody formBody = RequestBody.create(JSON_TYPE,gson.toJson(json));
//                OkHttpUtils.getmInstance(context).postAsyncHttp("http://www.mocky.io/v2/5d0351b130000067001f4ba2",
//                        formBody,new ResultCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        Log.d(TAG, "onError: "+e.toString());
//                        Toast.makeText(getApplicationContext(), "请求失败"+request.body().toString(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(String str) throws IOException {
//
//                        Log.d(TAG, "onResponse: "+str);
//                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
//                    }
//                });
            //卡片滑动页面示例
            //startActivity(new Intent(MainActivity.this,SwipeFinishActivity.class));

            //瀑布流示例
            startActivity(new Intent(MainActivity.this, WaterFallActivity.class));

            //3中Notification（通知）
            //startActivity(new Intent(MainActivity.this,NotificationActivity.class));

            //动态滑动Tab以及抽屉菜单界面
            //startActivity(new Intent(MainActivity.this,TabLayoutActivity.class));

            //方法④View动画
            //button.setAnimation(AnimationUtils.loadAnimation(context,R.anim.translate));
            //ObjectAnimator.ofFloat(button,"translationX",0,300).setDuration(1000).start();
            //方法⑤
            //button.scrollTo(-100,-100);
            //方法⑥
            //fab.smoothScrollerTo(-100,0);//向X轴右平移400像素

        });
        button.setOnClickListener(v -> {
            //ObjectAnimator核心
//                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(button,"translationX",200);
//                objectAnimator.setDuration(300);
//                objectAnimator.start();

            //ValueAnimator值动画
//                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,100);
//                valueAnimator.setTarget(testTv);
//                valueAnimator.setDuration(5000).start();
//                valueAnimator.addUpdateListener(animation -> {
//                    Float mFloat = (Float) animation.getAnimatedValue();
//                    Integer integer = mFloat.intValue();
//                    Log.d(TAG, "onAnimationUpdate: "+integer);
////                    testTv.setText(""+integer);
//                });

            //动画的监听
//                ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(testTv,"alpha",0);
//                objectAnimator.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                    }
//                });

            //组合动画--AnimatorSet
//                ObjectAnimator animator_one = ObjectAnimator.ofFloat(testTv,"translationX",
//                        0.0f,200.0f,0f);
//                ObjectAnimator animator_two = ObjectAnimator.ofFloat(testTv,"scaleX",
//                1.0f,2.0f);
//                ObjectAnimator animator_three = ObjectAnimator.ofFloat(testTv,"rotationX",
//                        0.0f,90.0f,0.0f);
//                AnimatorSet set = new AnimatorSet();
//                set.setDuration(1000);
//                set.play(animator_one).with(animator_two).after(animator_three);
//                set.start();

            //组合动画PropertyValuesHolder
//                PropertyValuesHolder valuesHolder_one = PropertyValuesHolder.ofFloat(
//                        "scaleX",1.0f,2.0f);
//                PropertyValuesHolder valuesHolder_two = PropertyValuesHolder.ofFloat(
//                        "rotationX",0.0f,90.0f,0.0f);
//                PropertyValuesHolder valuesHolder_three = PropertyValuesHolder.ofFloat(
//                        "alpha",1.0f,3.0f,1.0f);
//                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
//                        testTv,valuesHolder_one,valuesHolder_two,valuesHolder_three
//                );
//                objectAnimator.setDuration(1000).start();

            //在XML中使用属性动画
//                Animator animator = AnimatorInflater.loadAnimator(context,R.animator.scale);
//                animator.setTarget(testTv);
//                animator.start();
            ImageLoader.with(context)
                    .url("http://www.huaguangstore.com.cn/user_images/Koala.jpg")
                    .placeHolder(R.mipmap.user_unload)
                    .asCircle()
                    .override(50,50)
                    .rectRoundCorner(10)
                    .into(cardIv);

        });
    }

    @Override
    protected void initView() {
        context = this;
        fab = findViewById(R.id.fab_view);
        button= findViewById(R.id.my_btn);
        testTv =findViewById(R.id.test_tv);
        cardIv = findViewById(R.id.card_iv);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }
}
