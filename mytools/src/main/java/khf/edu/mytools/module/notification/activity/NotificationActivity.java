package khf.edu.mytools.module.notification.activity;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import khf.edu.mytools.R;
import khf.edu.mytools.common.util.NotificationUtil;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button commonBtn;
    private Button foldBtn;
    private Button hangBtn;
    private NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
        initNotification();
    }

    @SuppressLint("NewApi")
    private void initNotification() {
        //创建一个NotificationManager的引用
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    private void initView() {
        commonBtn  =findViewById(R.id.common_btn);
        foldBtn = findViewById(R.id.fold_btn);
        hangBtn = findViewById(R.id.hang_btn);
        commonBtn.setOnClickListener(this);
        foldBtn.setOnClickListener(this);
        hangBtn.setOnClickListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_btn:
                Toast.makeText(this,"普通通知",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fold_btn:
                NotificationUtil notificationUtil = new NotificationUtil(this);
                notificationUtil.sendNotification(getString(R.string.title),getString(R.string.content));
                Toast.makeText(this,"折叠式通知",Toast.LENGTH_SHORT).show();
                break;
            case R.id.hang_btn:
                Toast.makeText(this,"悬挂式通知",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
