package kehuafu.cn.tools.handler;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 功能描述：使用Handler时，设置activity或fragment的弱引用
 */
public class BaseHandler extends Handler {

    //弱引用
    private WeakReference<Object> weakReference;

    //消息处理接口
    private HandleMessage handleMessage;

    /**
     * 设置弱引用
     *
     * @param weakReference
     * @param handleMessage
     */
    public BaseHandler(WeakReference<Object> weakReference, HandleMessage handleMessage) {
        this.weakReference = weakReference;
        this.handleMessage = handleMessage;
    }

    public void handleMessage(Message msg) {
        handleMessage.onMessage(msg);
    }

    /**
     * 消息处理接口数据回调
     */
    public interface HandleMessage {
        void onMessage(Message msg);
    }
}
