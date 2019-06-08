package khf.edu.mytools.module.activity;

import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
    private View view;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        //使用BaseToast示例
        view = getLayoutInflater().inflate(kehuafu.cn.tools.R.layout.toast_tip, null);
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
        for (int i=0;i<10;i++){
            GetNextDayOnNew(i);
        }
    }
    /**
     * 利用j8的新特性，得到日期下一天更加方便了
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate GetNextDayOnNew(int i){
        // 取当前日期
        LocalDate localDate = LocalDate.now();
        // 打印当前日期
        System.out.println("日期: "+localDate);
        //当前对象减去指定的天数(一天)
        localDate = localDate.minusDays(-i);
        //打印减去一天的天数
        System.out.println("日期的下一天："+localDate);
        Log.i("TTTTTTTTTT",""+localDate.getDayOfWeek().toString());
        return localDate;
    }
}
