package khf.edu.mytools.module.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import khf.edu.mytools.R;
import khf.edu.mytools.module.adapter.FragmentAdapter;
import khf.edu.mytools.module.fragment.MyFragment;


public class FragmentDialog extends DialogFragment {
    private static final String TAG = "FragmentDialog";
    private Context context;
    private View view;
    private FragmentAdapter fragmentAdapter;
    private List<Fragment> list;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = this.getActivity();
        view = inflater.inflate(R.layout.dialog_layout, container, false);
        viewPager = view.findViewById(R.id.my_vp);
        list= new ArrayList<>();
        for (int i=0;i<3;i++){
            list.add(new MyFragment());
        }
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(),list);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
        return view;
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
}
