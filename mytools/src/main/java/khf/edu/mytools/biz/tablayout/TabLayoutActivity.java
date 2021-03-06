package khf.edu.mytools.biz.tablayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import khf.edu.mytools.R;
import khf.edu.mytools.biz.tablayout.adapter.FragmentAdapter;
import khf.edu.mytools.biz.tablayout.tab.ListFragment;

public class TabLayoutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @SuppressLint({"NewApi", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        Toolbar toolbar = findViewById(R.id.toolbal);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        viewPager = findViewById(R.id.viewpager);
        drawerLayout = findViewById(R.id.dl_main_drawer);
        drawerLayout.openDrawer(Gravity.START);
        NavigationView navigationView = findViewById(R.id.nv_main_navigation);
        if (navigationView!=null){
            navigationView.setNavigationItemSelectedListener(
                    menuItem -> {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    });

        }
        initViewPager();
    }

    private void initViewPager() {
        tabLayout = findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("体育");
        titles.add("巴萨");
        titles.add("购物");
        for (int i =0;i<titles.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for (int i=0;i<titles.size();i++){
            fragments.add(new ListFragment());
        }
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments,titles);
        //给ViewPager设置适配器
        viewPager.setAdapter(fragmentAdapter);
        //将TabLayout和ViewPager关联起来
        tabLayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);
    }
}
