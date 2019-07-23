package kehuafu.cn.tools.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * ConnectivityManager实现的实时网络监听
 */
public class RealTimeNetwork extends BroadcastReceiver {

    private static final String TAG = "RealTimeNetwork";

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            // WIFI 和 移动网络都关闭 即没有有效网络
            Log.d(TAG, "onReceive: 当前无网络连接");
            return;
        }
        String typeName = "";
        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
            //WIFI网络跳转的页面
            typeName = networkInfo.getTypeName();
        }else if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            //Mobile网络跳转的页面
            typeName = networkInfo.getTypeName();
        }
        Log.d(TAG, "onReceive: "+typeName);
        Log.d(TAG, "onReceive: "+networkInfo.getSubtypeName());
        Log.d(TAG, "onReceive: "+networkInfo.getDetailedState());
    }
}
