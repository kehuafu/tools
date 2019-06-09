package khf.edu.mytools.module.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import khf.edu.mytools.R;
import khf.edu.mytools.module.adapter.DateAdapter;
import khf.edu.mytools.module.adapter.FragmentAdapter;
import khf.edu.mytools.module.bean.LeaveBeanShell;
import khf.edu.mytools.module.fragment.MyFragment;


public class FragmentDialog extends DialogFragment implements MyFragment.AllClick {
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
    private List<String> date;
    private List<String> week;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_layout, container, false);
        initView();
        initListener();
        Bundle bundle = getArguments();
        if (bundle!=null){
            shells = (List<LeaveBeanShell>) bundle.getSerializable("shells");
        }
        if (list == null) {
            list = new LinkedList<>();
            for (int i = 0; i < 6; i++) {
                list.add(new MyFragment(shells, context, i, this));
            }
            Toast.makeText(context,"null:"+shells.size(),Toast.LENGTH_SHORT).show();
        }else {
            list.clear();
            list = new LinkedList<>();
            for (int i = 0; i < 6; i++) {
                list.add(new MyFragment(shells, context, i, this));
            }
            Toast.makeText(context,"NotNull:"+shells.size(),Toast.LENGTH_SHORT).show();
        }
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), list);
        myFragment = fragmentAdapter.getItem(0);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(6);
        viewPager.setCurrentItem(0);
        return view;
    }
    /**
     * 初始化控件
     */
    private void initView() {
        context = this.getActivity();
        viewPager = view.findViewById(R.id.my_vp);
        select_all_tv = view.findViewById(R.id.select_all_tv);
        date_rv = view.findViewById(R.id.date_rv);
        date = new LinkedList<>();
        week = new LinkedList<>();
        LinearLayoutManager layoutManager_course = new LinearLayoutManager(context);
        date_rv.setLayoutManager(layoutManager_course);
        layoutManager_course.setOrientation(OrientationHelper.HORIZONTAL);
        DateAdapter dateAdapter= new DateAdapter(context, date,week);
        date_rv.setAdapter(dateAdapter);
        dateAdapter.notifyDataSetChanged();
    }

    /**
     * 监听
     */
    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                myFragment = fragmentAdapter.getItem(i);
                updateAllSelectedUI(i);
                flag_fragment = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        /**
         * 全选点击
         */
        select_all_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFragment.reFreshUi(-1, flag_fragment);
            }
        });
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
            this.getDialog().setCanceledOnTouchOutside(true);//外部不可点击
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
     * 更新对应的Fragment的的全选按钮ui
     */
    private void updateAllSelectedUI(int i) {
        switch (i) {
            case 0:
                myFragment.reFreshUi(-2, i);
                break;
            case 1:
                myFragment.reFreshUi(-2, i);
                break;
            case 2:
                myFragment.reFreshUi(-2, i);
                break;
            case 3:
                myFragment.reFreshUi(-2, i);
                break;
            case 4:
                myFragment.reFreshUi(-2, i);
                break;
            case 5:
                myFragment.reFreshUi(-2, i);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shells.clear();
        dismiss();
    }
}
