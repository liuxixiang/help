package fangjs.fangjinsuo.com.help.http.service;

import java.util.List;
import java.util.Map;

import fangjs.fangjinsuo.com.help.bean.CustomerResponseBean;
import fangjs.fangjinsuo.com.help.http.ResponseResult;
import fangjs.fangjinsuo.com.help.http.HttpURLConstant;
import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by lxh on 2017/04/01.
 * Detail 默认基础的api 提供 get post download upload
 */
public interface ApiService {

    @POST("{path}")
    <T> Flowable<ResponseResult<T>> post(
            @Path(value = "path", encoded = true) String path,
            @QueryMap Map<String, Object> map);

    @POST("{path}")
    Flowable<ResponseBody> postJSON(
            @Path(value = "path", encoded = true) String path,
            @Body RequestBody route);


    @GET("{path}")
    <T> Flowable<ResponseResult<T>> get(
            @Path(value = "path", encoded = true) String path);

    @GET("{path}")
    Flowable<ResponseBody> get(
            @Path(value = "path", encoded = true) String path,
            @QueryMap Map<String, Object> map);

    @GET("{path}")
    Flowable<ResponseBody> getJSON(
            @Path(value = "path", encoded = true) String path,
            @Body RequestBody route);

//    统一用下面那个上传入口
//    @Multipart
//    @POST("{path}")
//    Observable<ResponseBody> upLoadFile(
//            @Path(value = "path",encoded = true) String url,
//            @Part("image\"; filename=\"image.jpg") RequestBody requestBody);


    @Multipart
    @POST()
    Flowable<ResponseBody> uploadFile(
            @Url String url,
            @PartMap() Map<String, RequestBody> maps);

    //支持大文件
//    @Streaming
    @GET
    Flowable<ResponseBody> downloadFile(
            @Url String fileUrl);



    /**
     * 获取客户列表
     * @return
     */
    @GET(HttpURLConstant.GET_CUSTOMER_LIST)
    Flowable<ResponseResult<List<CustomerResponseBean>>> getCustomerList();

}
