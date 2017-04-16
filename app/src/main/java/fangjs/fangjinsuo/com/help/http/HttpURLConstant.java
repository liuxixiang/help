package fangjs.fangjinsuo.com.help.http;

/**
 * Created by fangjinsuo.com on 2017/4/1.
 */

public class HttpURLConstant {
    public static final int CONNECT_TIME_OUT = 15;//连接超时
    public static final int READ_TIME_OUT = 15;//读超时
    public static final int WRITE_TIME_OUT = 15;//写超时

    public static final String BASE_URL = "http://192.168.3.74:8080/";
    public static final String API_V1 = "api/v1/";

    public static final String GET_CUSTOMER_LIST = API_V1 + "getCustomerList?customerId=1";
}
