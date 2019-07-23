package khf.edu.mytools.module.builder.toast;

import android.content.Context;
import android.widget.Toast;


/**
 * InnerBuilder的插件==>
 *  自动生成Builder模式
 */
public class CustomToast {

    private final Context mContext;
    private final String mMessage;
    private final int mBgColor;
    private final int mAlpha;

    private CustomToast(Builder builder) {
        mContext = builder.mContext;
        mMessage = builder.mMessage;
        mBgColor = builder.mBgColor;
        mAlpha = builder.mAlpha;
        Toast.makeText(mContext,mMessage,Toast.LENGTH_SHORT).show();
    }

    public static final class Builder {
        private final Context mContext;
        private String mMessage;
        private int mBgColor;
        private int mAlpha;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder message(String val) {
            mMessage = val;
            return this;
        }

        public Builder bgColcr(int val) {
            mBgColor = val;
            return this;
        }

        public Builder alpha(int val) {
            mAlpha = val;
            return this;
        }

        public CustomToast build() {
            return new CustomToast(this);
        }
    }
}
