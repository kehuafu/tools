package kehuafu.cn.tools.util;

import java.io.IOException;

import okhttp3.Request;

abstract class ResultCallback {
    abstract void onError(Request request, Exception e);

    abstract void onResponse(String str) throws IOException;
}
