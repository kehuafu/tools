package khf.edu.mytools.module.fragment;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kehuafu.cn.tools.util.BaseToast;
import khf.edu.mytools.R;
import khf.edu.mytools.module.adapter.LeaveAdapter;
import khf.edu.mytools.module.bean.LeaveBeanShell;

import static khf.edu.mytools.module.dialog.FragmentDialog.flag_fragment;

public class MyFragment extends Fragment implements ListView.OnItemClickListener {
    private Context context;
    private View view;
    private List<LeaveBeanShell> shells;
    private List<LeaveBeanShell.CourseBean> mData = new LinkedList<>();
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
        showList(mData);
        return view;
    }

    /**
     * 设置数据
     */
    private void setData() {
        int color = getResources().getColor(R.color.textColor);
        switch (position) {
            case 0:
                for (int i = 0; i < 1; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                shells.add(new LeaveBeanShell(mData, false));
                break;
            case 1:
                for (int i = 0; i < 2; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                shells.add(new LeaveBeanShell(mData, false));
                break;
            case 2:
                for (int i = 0; i < 3; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                shells.add(new LeaveBeanShell(mData, false));
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                shells.add(new LeaveBeanShell(mData, false));
                break;
            case 4:
                for (int i = 0; i < 5; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                shells.add(new LeaveBeanShell(mData, false));
                break;
            case 5:
                for (int i = 0; i < 6; i++) {
                    mData.add(new LeaveBeanShell.CourseBean("java EE框架应用开发" + i, color, false));
                }
                shells.add(new LeaveBeanShell(mData, false));
                break;
        }
    }

    /**
     * 通知ListView界面显示
     *
     * @param mData
     */
    private void showList(List<LeaveBeanShell.CourseBean> mData) {
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
        int color = getResources().getColor(R.color.colorDefault);//已选择的颜色
        int color_normal = getResources().getColor(R.color.textColor);//正常颜色
        if (position >= 0) {//点击listView中的item时的变化
            if (!mData.get(position).isChecked()) {
                mData.get(position).setChecked(true);
                mData.get(position).setColor(color);
                showList(mData);
                shells.set(flag_fragment, new LeaveBeanShell(mData, false));
            } else if (mData.get(position).isChecked()) {
                mData.get(position).setChecked(false);
                mData.get(position).setColor(color_normal);
                showList(mData);
                shells.set(flag_fragment, new LeaveBeanShell(mData, false));
            }
            for (int j = 0; j < mData.size(); j++) {
                if (!mData.get(j).isChecked()) {
                    allClick.AllClick(false);
                    shells.set(flag_fragment, new LeaveBeanShell(mData, false));
                    return;
                } else {
                    allClick.AllClick(true);
                    shells.set(flag_fragment, new LeaveBeanShell(mData, true));
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
                shells.set(flag_fragment, new LeaveBeanShell(mData, true));
            } else {
                for (int i = 0; i < mData.size(); i++) {
                    mData.get(i).setChecked(false);
                    allClick.AllClick(false);
                    mData.get(i).setColor(color_normal);
                    showList(mData);
                    shells.set(flag_fragment, new LeaveBeanShell(mData, false));
                }
            }
        } else {//滑动fragment时的变化
            if (!shells.get(flag_fragment).isAllcheck()) {
                allClick.AllClick(false);
            } else {
                allClick.AllClick(true);
            }
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
}
