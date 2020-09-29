package khf.edu.mytools.biz.leave.adapter;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import khf.edu.mytools.biz.leave.tab.MyFragment;

/**
 * DialogFragment的适配器
 * 作用：
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<MyFragment> list;

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
