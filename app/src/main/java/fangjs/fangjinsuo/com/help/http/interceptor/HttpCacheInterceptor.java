package fangjs.fangjinsuo.com.help.http.interceptor;

/**
 * Created by fangjinsuo.com on 2017/4/11.
 */

import android.content.Context;

import java.io.IOException;

import fangjs.fangjinsuo.com.help.utils.NetWorkUtils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求缓存策略插入器
 *
 * Created by XiaoFeng on 17/1/17.
 */
public class HttpCacheInterceptor implements Interceptor {
    private Context mContext;
    public HttpCacheInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // 无网络时，始终使用本地Cache
        if (!NetWorkUtils.isConnectedByState(mContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);
        if (NetWorkUtils.isConnectedByState(mContext)) {
            // 有网络时，设置缓存过期时间0个小时
            int maxAge = 0;
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma") // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            // 无网络时，设置缓存过期超时时间为4周
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }
}