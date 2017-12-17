package android.shineweather.com.shineweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by LSG on 2017/12/16.
 */

public class HttpUtil {
    public static void sendOKHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
