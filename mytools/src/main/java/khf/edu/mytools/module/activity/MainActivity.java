package khf.edu.mytools.module.activity;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kehuafu.cn.tools.util.BaseToast;
import kehuafu.cn.tools.util.SPUtils;
import khf.edu.mytools.R;
import khf.edu.mytools.module.adapter.LeaveAdapter;
import khf.edu.mytools.module.bean.LeaveBeanShell;
import khf.edu.mytools.module.dialog.Dialog;
import khf.edu.mytools.module.fragment.MyFragment;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private TextView click_tv;
    private Dialog dialog;
    private static List<LeaveBeanShell> shells;//存储课程数据信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        //使用BaseToast示例
        View view = getLayoutInflater().inflate(kehuafu.cn.tools.R.layout.toast_tip, null);
        BaseToast.showShort(view, "计算机网络实践教程");
        shells = new ArrayList<>();

        click_tv = findViewById(R.id.click_tv);
        click_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new Dialog();
                Bundle bundle = new Bundle();
                bundle.putSerializable("shells", (Serializable) shells);
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "dialog");
            }
        });
    }
}
