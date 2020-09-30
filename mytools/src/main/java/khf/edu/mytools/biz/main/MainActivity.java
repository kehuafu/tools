package khf.edu.mytools.biz.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

import kehuafu.cn.tools.framework.BaseActivity;
import khf.edu.mytools.R;
import khf.edu.mytools.biz.main.adapter.MainAdapter;


/**
 *
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void initListener() {
    }

    @Override
    protected void initView() {
        List<String> mList;
        mList = Arrays.asList(getResources().getStringArray(R.array.main_theme));
        setBarColor(getResources().getColor(R.color.color_b1_dark));
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter mainAdapter = new MainAdapter(mList);
        recycler.setAdapter(mainAdapter);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

}
