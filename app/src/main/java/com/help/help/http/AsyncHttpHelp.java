package com.help.help.http;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.help.help.HelpApp;
import com.help.help.common.Contanst;
import com.help.help.util.HelpUtil;
import com.help.help.util.RsaCryptoHelper;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * 获取一个httpClient
 * Created by 火蚁 on 15/4/13.
 */
public class AsyncHttpHelp {
    public final static String PRIVATE_TOKEN = "private_token";
    public final static String GITOSC_PRIVATE_TOKEN = "git@osc_token";

    public static void get(String url, HttpCallback handler) {
        RxVolley.get(url, handler);
    }

    public static void get(String url, HttpParams params, HttpCallback handler) {
        new RxVolley.Builder().url(url).params(params).cacheTime(15).callback(handler).doTask();
    }

    public static void post(String url, HashMap<String,Object> params, HttpCallback handler) {
        HttpParams httpParams = new HttpParams();
        httpParams.putJsonParams(JSON.toJSONString(params));
        RxVolley.jsonPost(HttpURLConstant.BASE_URL + url, httpParams, handler);
    }

    /**
     * 得到一个HttpParams
     * @param params
     * @return
     */
    private static HttpParams HttpParams(HashMap<String,Object> params) {
        HttpParams httpParams = getHttpParams(params);
        if(params != null && params.size() > 0) {
            java.util.Iterator it = params.entrySet().iterator();
            while (it.hasNext()) {
                // entry.getKey() 返回与此项对应的键
                // entry.getValue() 返回与此项对应的值
                Map.Entry entry = (Map.Entry) it.next();
                if(entry.getValue() instanceof File) {
                    httpParams.put(entry.getKey().toString(), (File) entry.getValue());
                }else if(entry.getValue() instanceof Integer) {
                    httpParams.put(entry.getKey().toString(), (Integer) entry.getValue());
                }else if(entry.getValue() instanceof byte[]) {
                    httpParams.put(entry.getKey().toString(), (byte[]) entry.getValue());
                }else {
                    httpParams.put(entry.getKey().toString(), entry.getValue().toString());
                }
            }
        }
        return httpParams;
    }

    private static HttpParams getHttpParams(HashMap<String,Object> params) {
        HttpParams httpParams = new HttpParams();
        if(params != null && params.size() > 0) {
            TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
            //把参数key 变成小写
            for (Map.Entry<String, Object> obj : params.entrySet()) {
                treeMap.put(obj.getKey().toLowerCase(), obj.getValue());
            }

            StringBuffer signText = new StringBuffer();
            for (Map.Entry<String, Object> obj : treeMap.entrySet()) {
                signText.append(obj.getKey());
                signText.append(checkType(obj.getValue()));
            }
            String privateKey =  HelpApp.getInstance().getProperty(Contanst
                    .PROP_KEY_PRIVATE_TOKEN);
//            String privateKey = PreferencesUtils.getString(mContext, "privateKey");
//            DebugLog.e("lxh","signText====" + signText.toString());
//            DebugLog.e("lxh", "privateKey====" + privateKey);
            try {
//                String sign = RsaCryptoHelper.encryptByPublicKey(privateKey, signText.toString());
                String sign = RsaCryptoHelper.signByPrivateKey(privateKey, signText.toString());
                httpParams.putHeaders("X-HELP-SIGN", sign);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
           httpParams.putHeaders("X-HELP-SIGN", "");
        }
        return httpParams;
    }

    /**
     * 检查参数类型 返回相对应的数据
     * @param object
     * @return
     */
    private static String checkType(Object object) {
        if (object instanceof Date) {
            //时间戳
            return ((Date) object).getTime() + "";
        } else if (object instanceof Float || object instanceof Double) {
            //去除.0
            return HelpUtil.subZeroAndDot(object + "");
        } else if (object instanceof Boolean) {
            boolean boolValue = (boolean) object;
            if (boolValue) {
                return "1";
            } else {
                return "0";
            }
        } else {
            if (object != null) {
                return object.toString();
            }
            return "";
        }
    }

    /**
     * 获得UserAgent
     *
     * @return
     */
    private static String getUserAgent() {
        HelpApp appContext = HelpApp.getInstance();
        StringBuilder ua = new StringBuilder("help.com");
        ua.append('/' + appContext.getPackageInfo().versionName + '_' + appContext.getPackageInfo
                ().versionCode);//App版本
        ua.append("/Android");//手机系统平台
        ua.append("/" + android.os.Build.VERSION.RELEASE);//手机系统版本
        ua.append("/" + android.os.Build.MODEL); //手机型号
        ua.append("/" + HelpApp.getInstance().getAppId());//客户端唯一标识
        return ua.toString();
    }

    public static HttpParams getPrivateTokenWithParams() {
        HttpParams params = new HttpParams();
        params.putHeaders("User-Agent", getUserAgent());
        String private_token = HelpApp.getInstance().getProperty(PRIVATE_TOKEN);
//        private_token = CyptoUtils.decode(GITOSC_PRIVATE_TOKEN, private_token);
        if (!TextUtils.isEmpty(private_token))
            params.put(PRIVATE_TOKEN, private_token);
        return params;
    }

    public static HttpParams getHttpParams() {
        return getPrivateTokenWithParams();
    }
}
