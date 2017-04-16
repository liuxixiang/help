package fangjs.fangjinsuo.com.help.http;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fangjs.fangjinsuo.com.help.BuildConfig;
import fangjs.fangjinsuo.com.help.utils.NetWorkUtils;
import fangjs.fangjinsuo.com.help.app.App;
import fangjs.fangjinsuo.com.help.app.AppConfig;
import fangjs.fangjinsuo.com.help.http.service.ApiService;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lxh on 2017/04/11.
 */
public class RetrofitUtils {

    private static ApiService sApiService = null;
    private static Retrofit sRetrofit = null;
    private static OkHttpClient sOkHttpClient = null;

    private void init() {
        initOkHttp();
        initRetrofit();
        sApiService = sRetrofit.create(ApiService.class);
    }

    private RetrofitUtils() {
        init();
    }

    public static RetrofitUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitUtils INSTANCE = new RetrofitUtils();
    }

    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        // 缓存
        File cacheFile = new File(App.getAppCacheDir(), AppConfig.Cache.NET_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtils.isConnectedByState(App.getAppContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                Response.Builder newBuilder = response.newBuilder();
                if (NetWorkUtils.isConnectedByState(App.getAppContext())) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    newBuilder.header("Cache-Control", "public, max-age=" + maxAge);
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    newBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
                }
                return newBuilder.build();
            }
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);
        //设置头文件
        //builder.addInterceptor(new HttpHeaderInterceptor());
        //设置超时
        builder.connectTimeout(HttpURLConstant.CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(HttpURLConstant.READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(HttpURLConstant.WRITE_TIME_OUT, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        sOkHttpClient = builder.build();
    }

    private static void initRetrofit() {
        sRetrofit = new Retrofit.Builder()
                .baseUrl(HttpURLConstant.BASE_URL)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 默认获取ApiService
     * @return
     */
    public ApiService getApiService() {
        return sApiService;
    }


    /**
     * 获取传递的ApiService
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getApiService(Class<T> clazz) {
        return sRetrofit.create(clazz);
    }

}
