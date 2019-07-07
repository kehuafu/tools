package khf.edu.mytools.module.waterfall.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.LinkedList;
import java.util.List;

import kehuafu.cn.tools.framework.BaseActivity;
import khf.edu.mytools.R;
import khf.edu.mytools.module.waterfall.adapter.WaterFallAdapter;

public class WaterFallActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private WaterFallAdapter waterFallAdapter;
    private RecyclerView recyclerView;
    private List<String> mList;
    private List<Integer> mHeight;
    private ImageView cameraIv;
    private ImageView menuIv;
    @Override
    protected void initListener() {
        //监听item点击事件
        waterFallAdapter.setOnItemClickListener(new WaterFallAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "您点击了第" + position + "项目", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                waterFallAdapter.removeData(position);
            }
        });
    }

    protected void initView() {
        mContext = this;
        mList = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("" + i);
        }
        mHeight = new LinkedList<>();
        for (int i = 0; i < mList.size(); i++) {
            mHeight.add((int) (600 + Math.random() * 300));
        }
        recyclerView = findViewById(R.id.my_rv);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置item增加和删除时的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        waterFallAdapter = new WaterFallAdapter(this, mList,mHeight);
        recyclerView.setAdapter(waterFallAdapter);
        //设置瀑布流
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        cameraIv = findViewById(R.id.camera_iv);
        menuIv = findViewById(R.id.menu_iv);
        cameraIv.setOnClickListener(this);
        menuIv.setOnClickListener(this);
    }

    @Override
    protected int setLayout() {
        return R.layout.waterfall_activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.camera_iv:
                break;
            case R.id.menu_iv:
                break;
        }
    }
}