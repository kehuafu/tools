package khf.edu.mytools.module.annotation.activity;

import android.Manifest;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
