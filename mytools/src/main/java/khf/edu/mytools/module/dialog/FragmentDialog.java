package khf.edu.mytools.module.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import kehuafu.cn.tools.util.DateUtils;
import kehuafu.cn.tools.util.SPUtils;
import khf.edu.mytools.R;
import khf.edu.mytools.helper.SwipeBackLayout;
import khf.edu.mytools.module.adapter.DateAdapter;
import khf.edu.mytools.module.adapter.FragmentAdapter;
import khf.edu.mytools.module.bean.LeaveBeanShell;
import khf.edu.mytools.module.bean.LeaveDate;
import khf.edu.mytools.module.fragment.MyFragment;


public class FragmentDialog extends DialogFragment implements MyFragment.AllClick, DateAdapter.OnclickDate, View.OnClickListener {
    private static final String TAG = "FragmentDialog";
    private Context context;
    private View view;
    private FragmentAdapter fragmentAdapter;
    private List<MyFragment> list;
    private ViewPager viewPager;
    private TextView select_all_tv;
    private List<LeaveBeanShell> shells;//存储课程数据信息
    public static int flag_fragment = 0;//当前fragment的位置
    private MyFragment myFragment;
    private RecyclerView date_rv;
    private List<String> leftDate;
    private List<String> leftWeek;
    private List<String> rightDate;
    private List<String> rightWeek;
    private List<LeaveDate> leaveDates;
    private DateAdapter dateAdapter;
    private LinearLayoutManager layoutManager_course;
    private RelativeLayout date_left_rl;
    private RelativeLayout date_right_rl;
    private TextView date_tv;
    private SwipeBackLayout mSwipeBackLayout;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_layout, container, false);
        context = getContext();
        initData();
        initView();
        initListener();
        mSwipeBackLayout = new SwipeBackLayout(context);
        mSwipeBackLayout.attachToFragment(this);
        mSwipeBackLayout.setDirectionMode(SwipeBackLayout.FROM_TOP);
        return view;
    }

    /**
     * 初始化数据
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initData() {
        Bundle bundle = getArguments();
        leftDate = new LinkedList<>();
        leftWeek = new LinkedList<>();
        leaveDates = new LinkedList<>();
        rightDate = new LinkedList<>();
        rightWeek = new LinkedList<>();
        /**
         * 模拟课程数据
         */
        if (bundle != null) {
            shells = (List<LeaveBeanShell>) bundle.getSerializable("shells");
        }
        if (list == null) {
            list = new LinkedList<>();
            for (int i = 0; i < 6; i++) {
                list.add(new MyFragment(shells, context, i, this));
            }
        } else {
            list.clear();
            list = new LinkedList<>();
            for (int i = 0; i < 6; i++) {
                list.add(new MyFragment(shells, context, i, this));
            }
        }
        /**
         * 左边日期
         */
        for (int i = 0; i < 5; i++) {
            leftDate.add(DateUtils.GetNextDayOnNew(i).toString().substring(8, 10));
            leftWeek.add(DateUtils.GetWeekOnNow(i));
            if (i == 0) {
                SPUtils.instance().save("date", 0);//初始化当前日期item的位置
                leaveDates.add(new LeaveDate(R.drawable.online_date_bg, context.getResources().getColor(R.color.colorWhite),
                        context.getResources().getColor(R.color.colorWhite)));
            } else {
                leaveDates.add(new LeaveDate(R.drawable.online_date_normal_bg, context.getResources().getColor(R.color.textColor),
                        context.getResources().getColor(R.color.weekColor)));
            }
        }
        /**
         * 右边日期
         */
        for (int j = 5; j < 10; j++) {
            rightDate.add(DateUtils.GetNextDayOnNew(j).toString().substring(8, 10));
            rightWeek.add(DateUtils.GetWeekOnNow(j));
        }
    }

    /**
     * 初始化控件
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initView() {
        viewPager = view.findViewById(R.id.my_vp);
        select_all_tv = view.findViewById(R.id.select_all_tv);
        date_rv = view.findViewById(R.id.date_rv);
        date_left_rl = view.findViewById(R.id.date_left_rl);
        date_left_rl.setEnabled(false);
        date_right_rl = view.findViewById(R.id.date_right_rl);
        date_tv = view.findViewById(R.id.date_tv);
        UpdateYearAndMonth(0);
        //设置日期中RecyclerView的布局管理
        layoutManager_course = new LinearLayoutManager(context);
        date_rv.setLayoutManager(layoutManager_course);
        layoutManager_course.setOrientation(OrientationHelper.HORIZONTAL);
        setDate(leftDate, leftWeek);
        //设置Fragment碎片的适配器
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), list);
        myFragment = fragmentAdapter.getItem(0);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(6);
        viewPager.setCurrentItem(0);
    }

    /**
     * 设置日期与星期的数据
     *
     * @param date
     * @param week
     */
    public void setDate(List<String> date, List<String> week) {
        if (dateAdapter != null) {
            dateAdapter = null;
        }
        dateAdapter = new DateAdapter(context, date, week, leaveDates, this);
        date_rv.setAdapter(dateAdapter);
        dateAdapter.notifyDataSetChanged();
    }
    /**
     * 监听ViewPager的滑动以及全选的点击事件
     */
    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onPageSelected(int i) {
                myFragment = fragmentAdapter.getItem(i);
                updateAllSelectedUI(i);
                flag_fragment = i;
                if (i == 5) {
                    SPUtils.instance().save("date", 0);
                } else {
                    SPUtils.instance().save("date", i);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        date_left_rl.setOnClickListener(this);
        date_right_rl.setOnClickListener(this);
        select_all_tv.setOnClickListener(this);
    }

    /**
     * 初始化Dialog的样式
     */
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            //如果宽高都为MATCH_PARENT,内容外的背景色就会失效，所以只设置宽全屏
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);//全屏
            dialog.getWindow().setGravity(Gravity.BOTTOM);//内容设置在底部
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            //设置dialog的动画
            lp.windowAnimations = R.style.dialog_animation;
            dialog.getWindow().setAttributes(lp);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
            //内容的背景色,对于全屏很重要，系统的内容宽度是不全屏的，替换为自己的后宽度可以全屏
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getDialog().setCanceledOnTouchOutside(false);//外部不可点击
        }
    }

    /**
     * 全选的UI更新
     *
     * @param allclick
     */
    @Override
    public void AllClick(boolean allclick) {
        if (allclick) {
            select_all_tv.setText("取消全选");
        } else {
            select_all_tv.setText("全选");
        }
    }

    /**
     * 更新年份、月份的UI
     */
    @SuppressLint({"NewApi", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void UpdateYearAndMonth(int i){
        date_tv.setText(DateUtils.GetNextDayOnNew(i).toString().substring(0,4)+ "年"+
                DateUtils.GetNextDayOnNew(i).toString().substring(5,7)+"月");
    }
    /**
     * 更新对应的Fragment的的全选按钮ui
     */
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateAllSelectedUI(int i) {
        switch (i) {
            case 0:
                setDate(leftDate, leftWeek);
                UpdateYearAndMonth(i);
                myFragment.reFreshUi(-2, i);
                dateAdapter.onRefresh(SPUtils.instance().get("date", 0), 0);
                break;
            case 1:
                setDate(leftDate, leftWeek);
                UpdateYearAndMonth(i);
                myFragment.reFreshUi(-2, i);
                dateAdapter.onRefresh(SPUtils.instance().get("date", 0), 1);
                break;
            case 2:
                setDate(leftDate, leftWeek);
                UpdateYearAndMonth(i);
                myFragment.reFreshUi(-2, i);
                dateAdapter.onRefresh(SPUtils.instance().get("date", 0), 2);
                break;
            case 3:
                setDate(leftDate, leftWeek);
                UpdateYearAndMonth(i);
                myFragment.reFreshUi(-2, i);
                dateAdapter.onRefresh(SPUtils.instance().get("date", 0), 3);
                break;
            case 4:
                setDate(leftDate, leftWeek);
                UpdateYearAndMonth(i);
                myFragment.reFreshUi(-2, i);
                dateAdapter.onRefresh(SPUtils.instance().get("date", 0), 4);
                break;
            case 5:
                setDate(rightDate, rightWeek);
                UpdateYearAndMonth(i);
                myFragment.reFreshUi(-2, i);
                dateAdapter.onRefresh(SPUtils.instance().get("date", 0), 0);
                break;
        }
    }

    /**
     * 点击日期的item后，切换到课程页面对应viewPager的item
     */
    @Override
    public void onClickDate(int position) {
        viewPager.setCurrentItem(position, true);
    }

    /**
     * 左右按钮、全选的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_left_rl:
                viewPager.setCurrentItem(SPUtils.instance().get("position",0),true);
                date_left_rl.setEnabled(false);
                date_right_rl.setEnabled(true);
                break;
            case R.id.date_right_rl:
                Toast.makeText(context,""+flag_fragment,Toast.LENGTH_SHORT).show();
                SPUtils.instance().save("position",flag_fragment);
                viewPager.setCurrentItem(5,true);
                date_left_rl.setEnabled(true);
                date_right_rl.setEnabled(false);
                break;
            case R.id.select_all_tv:
                myFragment.reFreshUi(-1, flag_fragment);
                break;
        }
    }
    /**
     * 释放资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        shells.clear();
        //dismiss();
        SPUtils.instance().clear("date");
    }
}
