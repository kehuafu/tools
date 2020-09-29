package khf.edu.mytools.biz.main;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kehuafu.cn.tools.custom.MyTextView;
import kehuafu.cn.tools.custom.RoundImageView;
import kehuafu.cn.tools.framework.BaseActivity;
import kehuafu.cn.tools.imageloader.DoubleCache;
import kehuafu.cn.tools.imageloader.ImageLoader;
import kehuafu.cn.tools.util.BaseToast;
import kehuafu.cn.tools.util.RealTimeNetwork;
import khf.edu.mytools.R;
import khf.edu.mytools.biz.leave.bean.LeaveBeanShell;
import khf.edu.mytools.biz.leave.dialog.Dialog;
import okhttp3.MediaType;


public class MainActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
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
    private View fab;
    private Button button;
    private MyTextView myTextView;
    private ImageView cardIv;
    private RoundImageView mineIv;
    private ViewStub viewStub;
    private IntentFilter intentFilter;
    private RealTimeNetwork realTimeNetwork;


    @SuppressLint({"ClickableViewAccessibility", "InflateParams"})
    @Override
    protected void initListener() {
        myTextView.setOnClickListener(this);
        myTextView.setOnTouchListener(this);
        //使用BaseToast示例
        view = getLayoutInflater().inflate(kehuafu.cn.tools.R.layout.toast_tip, null);
        BaseToast.showShort(view, "计算机网络实践教程");

        shells = new ArrayList<>();
        click_tv = findViewById(R.id.click_tv);
        click_tv.setOnClickListener(v -> {
            //自定义对话框使用示例
            dialog = new Dialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("shells", (Serializable) shells);
            dialog.setArguments(bundle);
            dialog.show(getSupportFragmentManager(), "dialog");

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
            //startActivity(new Intent(MainActivity.this, WaterFallActivity.class));

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
//            ImageLoader.with(context)
//                    .url("http://www.huaguangstore.com.cn/user_images/Koala.jpg")
//                    .placeHolder(R.mipmap.user_unload)
//                    .asCircle()
//                    .override(50,50)
//                    .rectRoundCorner(10)
//                    .into(cardIv);
            ImageLoader imageLoader = new ImageLoader();
            //使用内存缓存
            //imageLoader.setImageCache(new MemoryCache());
            //使用SD卡缓存
            //imageLoader.setImageCache(new DiskCache());
            //使用双缓存缓存
            imageLoader.setImageCache(new DoubleCache());
            imageLoader.displayImage("http://www.huaguangstore.com.cn/user_images/yuge.webp", cardIv);

            //Snackbar提示栏
//            Snackbar.make(button,"Here 's a Snackbar",Snackbar.LENGTH_LONG)
//                    .setAction("Action",null)
//                    .show();
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        context = this;
        setBarColor(getResources().getColor(R.color.colorDefault));
        fab = findViewById(R.id.fab_view);
        button = findViewById(R.id.my_btn);
        viewStub = findViewById(R.id.loading_stub);
        viewStub.inflate();
        cardIv = findViewById(R.id.error_view);
        myTextView = findViewById(R.id.test_tv);
        mineIv = findViewById(R.id.mine_iv);
        //属性动画,我的头像
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scale);
        animator.setTarget(mineIv);
        animator.start();
        //实时网络监听
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        realTimeNetwork = new RealTimeNetwork();
        registerReceiver(realTimeNetwork,intentFilter);

    }

    /**
     * 分发
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.d(TAG, "dispatchTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                //Log.d(TAG, "dispatchTouchEvent:ACTION_UP ");
                break;
            case MotionEvent.ACTION_CANCEL:
                // Log.d(TAG, "dispatchTouchEvent: ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);//当为true时不再向下分发事件
    }

    /**
     * 消费
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent:ACTION_DOWN ");
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
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_tv:
                Log.d(TAG, "onClick: myTextView");
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        switch (v.getId()) {
//            case R.id.test_tv:
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Log.d(TAG, "onTouch: ACTION_DOWN");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Log.d(TAG, "onTouch: ACTION_MOVE");
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.d(TAG, "onTouch: ACTION_UP");
//                        break;
//                    case MotionEvent.ACTION_CANCEL:
//                        Log.d(TAG, "onTouch: ACTION_CANCEL");
//                        break;
//                    default:
//                        break;
//                }
//                break;
//        }
        return false;
    }

    //是否关闭广播接收器
    private void enableBroadcastReceiver(boolean isEnabled, Class<?> receiver) {
        PackageManager pm = getPackageManager();
        ComponentName receiverName = new ComponentName(this, receiver);
        int newState;
        if (isEnabled) {
            newState = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
        } else {
            newState = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        }
        pm.setComponentEnabledSetting(receiverName, newState, PackageManager.DONT_KILL_APP);
    }

    //禁用位置监听器
    private void disableLocaltionListener(LocationListener listener) {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            return;
        }
        locationManager.removeUpdates(listener);
    }

    /**
     * 以主线程中的文件写入为例，引起违例警告的代码
     */
    public void writeToExternalStorage() {
        File externalStorage = Environment.getExternalStorageDirectory();
        File destFile = new File(externalStorage, "dest.txt");
        try {
            OutputStream output = new FileOutputStream(destFile, true);
            output.write("droidyue.com".getBytes());
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(realTimeNetwork);
    }
}
