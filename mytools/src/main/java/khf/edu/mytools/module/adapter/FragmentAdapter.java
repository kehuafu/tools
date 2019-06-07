package khf.edu.mytools.module.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

import khf.edu.mytools.module.fragment.MyFragment;

/**
 * DialogFragment的适配器
 * 作用：
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<MyFragment> list;

    public FragmentAdapter(FragmentManager fm){
        super(fm);
    }
    public FragmentAdapter(FragmentManager fm,List<MyFragment> fragmentList) {
        super(fm);
        this.list = fragmentList;
    }

    @Override
    public MyFragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
