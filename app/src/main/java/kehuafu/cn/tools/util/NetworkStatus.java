package kehuafu.cn.tools.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 对联网状态的简单封装
 */
public class NetworkStatus {

    private Context context;
    private ConnectivityManager cm;
    private NetworkInfo info;

    /**
     * WIFI状态 ==1
     */
    public static final int TYPE_WIFI = ConnectivityManager.TYPE_WIFI;
    /**
     * 流量状态 == 0
     */
    public static final int TYPE_MOBILE = ConnectivityManager.TYPE_MOBILE;
    /**
     * 断网状态 ==-1
     */
    public static final int TYPE_NONETWORK = -1;

    public NetworkStatus(Context context) {
        this.context = context;
    }

    /**
     * 获取联网实例
     */
    private void getNetWorkInfo() {
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        info = cm.getActiveNetworkInfo();
    }

    /**
     * 判断是否有联网
     *
     * @return
     */
    public boolean isNetAvilable() {
        if (cm == null && info == null) {
            getNetWorkInfo();
        }
        return info != null && info.isAvailable();
    }

    /**
     * 判断是否wifi连接
     *
     * @return
     */
    public boolean isWifiConnected() {
        if (cm == null && info == null) {
            getNetWorkInfo();
        }
        return TYPE_WIFI == info.getType();
    }

    /**
     * 获取联网状态
     *
     * @return
     */
    public int getNetWorkStatus() {
        getNetWorkInfo();
        if (info != null && info.isConnected()) {
            return info.getType();
        } else {
            return TYPE_NONETWORK;
        }
    }
}
