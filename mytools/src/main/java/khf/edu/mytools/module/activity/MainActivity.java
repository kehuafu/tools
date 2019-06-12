package khf.edu.mytools.module.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kehuafu.cn.tools.util.BaseToast;
import kehuafu.cn.tools.util.OkHttpUtils;
import kehuafu.cn.tools.util.ResultCallback;
import khf.edu.mytools.R;
import khf.edu.mytools.module.bean.LeaveBeanShell;
import khf.edu.mytools.module.dialog.Dialog;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;


public class MainActivity extends AppCompatActivity {
    private Context context;
    private TextView click_tv;
    private Dialog dialog;
    private static List<LeaveBeanShell> shells;//存储课程数据信息
    private View view;
    private static final String TAG = "MainActivity";
    private static final MediaType JSON_TYPE = MediaType.parse("application/json;charset=utf-8");
    private static final String HEADER_NAME = "Authorization";
    private static final String HEADER_CONTENT = "Bearer";

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
                //自定义对话框使用示例
//                dialog = new Dialog();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("shells", (Serializable) shells);
//                dialog.setArguments(bundle);
//                dialog.show(getSupportFragmentManager(), "dialog");

                //异步post请求示例
                Map<String, Object> json = new HashMap<>();
                Gson gson = new Gson();
                json.put("studentID", "");
                RequestBody formBody = RequestBody.create(JSON_TYPE,gson.toJson(json));
                OkHttpUtils.getmInstance(context).postAsyncHttp("http://508cst.gcu.edu.cn/askOff/app/courseSchedule",formBody,"",new ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.d(TAG, "onError: "+e.toString());
                        Toast.makeText(getApplicationContext(), "请求失败"+request.body().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String str) throws IOException {

                        Log.d(TAG, "onResponse: "+str);
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
