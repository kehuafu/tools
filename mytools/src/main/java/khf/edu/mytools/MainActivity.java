package khf.edu.mytools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kehuafu.cn.tools.util.BaseToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用BaseToast示例
        View view = getLayoutInflater().inflate(kehuafu.cn.tools.R.layout.toast_tip,null);
        BaseToast.showShort(view,"计算机网络实践教程");
    }
}
