package khf.edu.mytools.module.annotation.activity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import khf.edu.mytools.R;

public class AnnotationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        requestNetWork("koko");
    }

    @RequiresPermission(Manifest.permission.INTERNET)
    private void requestNetWork(@NonNull String s) {
        Toast.makeText(this, "请求网络" + s, Toast.LENGTH_SHORT).show();
    }
}
