package kehuafu.cn.tools.module.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import khf.edu.mytools.R;
import khf.edu.mytools.module.adapter.LeaveAdapter;
import khf.edu.mytools.module.bean.LeaveBeanShell;

import static khf.edu.mytools.module.dialog.FragmentDialog.flag_fragment;

public class MyFragment extends Fragment implements ListView.OnItemClickListener {
    private Context context;
    private View view;
    private List<LeaveBeanShell> shells;
    private LeaveBeanShell shell ;
    private List<LeaveBeanShell.CourseBean> mData = new LinkedList<>();//模拟数据
    private LeaveAdapter schoolAdapter = null;
    private ListView listView;
    private int position;//Fragment的位置
    private AllClick allClick;

    public MyFragment() {
        super();
    }

    @SuppressLint("ValidFragment")
    public MyFragment(List<LeaveBeanShell> shells, Context context, int position, AllClick allClick) {
        this.shells = shells;
        this.context = context;
        this.position = position;
        this.allClick = allClick;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);
        setData();
        showList(shells.get(position).getCourse());
        return view;
    }

    /**
     * 模拟：设置数据
     */
    private void setData() {
        int color = context.getResources().getColor(R.color.textColor);
        switch (position) {
            case 0:
                for (int i = 0; i < 1; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                if (shells.size()<6) {
                    shells.add(new LeaveBeanShell(mData, false));
                }
                break;
            case 1:
                for (int i = 0; i < 2; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                if (shells.size()<6) {
                    shells.add(new LeaveBeanShell(mData, false));
                }
                break;
            case 2:
                for (int i = 0; i < 3; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                if (shells.size()<6) {
                    shells.add(new LeaveBeanShell(mData, false));
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                if (shells.size()<6) {
                    shells.add(new LeaveBeanShell(mData, false));
                }
                break;
            case 4:
                for (int i = 0; i < 5; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                if (shells.size()<6) {
                    shells.add(new LeaveBeanShell(mData, false));
                }
                break;
            case 5:
                for (int i = 0; i < 6; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                if (shells.size()<6) {
                    shells.add(new LeaveBeanShell(mData, false));
                }
                break;
        }
        shell = new LeaveBeanShell(mData, false);
    }

    /**
     * 通知ListView界面显示
     *
     * @param mData
     */
    public void showList(List<LeaveBeanShell.CourseBean> mData) {
        if (schoolAdapter == null) {
            listView = view.findViewById(R.id.list_lv);
            schoolAdapter = new LeaveAdapter((LinkedList<LeaveBeanShell.CourseBean>) mData, context);
            listView.setDividerHeight(0);
            listView.setAdapter(schoolAdapter);
            listView.setOnItemClickListener(this);
        } else {
            schoolAdapter.onRefresh((LinkedList<LeaveBeanShell.CourseBean>) mData);
        }
    }

    /**
     * 刷新ListView的UI
     */
    public void reFreshUi(int position, int flag_fragment) {
        int color = context.getResources().getColor(R.color.colorDefault);//已选择的颜色
        int color_normal = context.getResources().getColor(R.color.textColor);//正常颜色
        //mData = shells.get(flag_fragment).getCourse();
        mData=shells.get(flag_fragment).getCourse();
        if (position >= 0) {//点击listView中的item时的变化
            if (!mData.get(position).isChecked()) {
                mData.get(position).setChecked(true);
                mData.get(position).setColor(color);
                showList(mData);
                shell.setAllcheck(false);
                shells.set(flag_fragment, shell);
            } else if (mData.get(position).isChecked()) {
                mData.get(position).setChecked(false);
                mData.get(position).setColor(color_normal);
                showList(mData);
                shell.setAllcheck(false);
                shells.set(flag_fragment, shell);
            }
            for (int j = 0; j < mData.size(); j++) {
                if (!mData.get(j).isChecked()) {
                    allClick.AllClick(false);
                    shell.setAllcheck(false);
                    shells.set(flag_fragment, shell);
                    return;
                } else {
                    allClick.AllClick(true);
                    shell.setAllcheck(true);
                    shells.set(flag_fragment,shell);
                }
            }
        } else if (position == -1) {//点击全选时的变化
            if (!shells.get(flag_fragment).isAllcheck()) {
                for (int i = 0; i < mData.size(); i++) {
                    mData.get(i).setChecked(true);
                    allClick.AllClick(true);
                    mData.get(i).setColor(color);
                    showList(mData);
                }
                shell.setAllcheck(true);
                shells.set(flag_fragment, shell);
            } else {
                for (int i = 0; i < mData.size(); i++) {
                    mData.get(i).setChecked(false);
                    allClick.AllClick(false);
                    mData.get(i).setColor(color_normal);
                    showList(mData);
                    shell.setAllcheck(false);
                    shells.set(flag_fragment, shell);
                }
            }
        } else {//滑动fragment时的变化
            if (!shells.get(flag_fragment).isAllcheck()) {
                allClick.AllClick(false);
            } else {
                allClick.AllClick(true);
            }
            showList(shells.get(flag_fragment).getCourse());
        }
    }

    /**
     * ListView的item点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        reFreshUi(position, flag_fragment);
    }

    /**
     * 接口回调
     */
    public interface AllClick {
        void AllClick(boolean allclick);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
