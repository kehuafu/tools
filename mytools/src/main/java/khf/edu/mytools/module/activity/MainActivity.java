package khf.edu.mytools.module.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import kehuafu.cn.tools.util.BaseToast;
import khf.edu.mytools.R;
import khf.edu.mytools.module.adapter.FragmentAdapter;
import khf.edu.mytools.module.dialog.FragmentDialog;

public class MainActivity extends AppCompatActivity {
    private TextView click_tv;
    private FragmentDialog fragmentDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用BaseToast示例
        View view = getLayoutInflater().inflate(kehuafu.cn.tools.R.layout.toast_tip,null);
        BaseToast.showShort(view,"计算机网络实践教程");
        fragmentDialog = new FragmentDialog();

        click_tv = findViewById(R.id.click_tv);
        click_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentDialog.show(getSupportFragmentManager(),"");
            }
        });
    }
}
