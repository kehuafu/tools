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
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import kehuafu.cn.tools.util.BaseToast;
import khf.edu.mytools.R;
import khf.edu.mytools.module.adapter.FragmentAdapter;
import khf.edu.mytools.module.adapter.SchoolAdapter;
import khf.edu.mytools.module.bean.SchoolBeanShell;

public class MyFragment extends Fragment {
    private Context context;
    private View view;

    private List<SchoolBeanShell.SchoolBean> mData = null;
    private SchoolAdapter schoolAdapter = null;
    private ListView listView;
    private int position;

    public MyFragment(){
        super();
    }
    @SuppressLint("ValidFragment")
    public MyFragment(int position){
        this.position = position;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = this.getActivity();
        view = inflater.inflate(R.layout.fragment,container,false);
        setData();
        showList(mData);
        return view;
    }

    /**
     * 通知ListView界面显示
     * @param mData
     */
    private void showList(List<SchoolBeanShell.SchoolBean> mData) {
        if (schoolAdapter==null){
            listView = view.findViewById(R.id.list_lv);
            schoolAdapter = new SchoolAdapter((LinkedList<SchoolBeanShell.SchoolBean>) mData,context);
            listView.setDividerHeight(0);
            listView.setAdapter(schoolAdapter);
        }else {
            schoolAdapter.onDataChange((LinkedList<SchoolBeanShell.SchoolBean >)mData);
        }
    }

    /**
     * 设置数据
     */
    private void setData() {
        mData = new LinkedList<>();
        int color = getResources().getColor(R.color.colorDefault);
        switch (position) {
            case 0:
                for (int i=0;i<1;i++){
                    mData.add(new SchoolBeanShell.SchoolBean(""+i,color));
                }
                break;
            case 1:
                for (int i=0;i<2;i++){
                    mData.add(new SchoolBeanShell.SchoolBean(""+i,color));
                }
                break;
            case 2:
                for (int i=0;i<3;i++){
                    mData.add(new SchoolBeanShell.SchoolBean(""+i,color));
                }
                break;
            case 3:
                for (int i=0;i<4;i++){
                    mData.add(new SchoolBeanShell.SchoolBean(""+i,color));
                }
                break;
            case 4:
                for (int i=0;i<5;i++){
                    mData.add(new SchoolBeanShell.SchoolBean(""+i,color));
                }
                break;
        }
    }

    /**
     * 刷新ListView的UI
     */
    public void reFlashUi(){
        int color = getResources().getColor(R.color.colorPrimary);
        mData.add(new SchoolBeanShell.SchoolBean(""+2222,color));
        showList(mData);
    }
}
