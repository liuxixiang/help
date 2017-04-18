package fangjs.fangjinsuo.com.help.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class CustomerResponseBean implements Serializable {


    /**
     * wendu : 15
     * ganmao : 虽然温度适宜但风力较大，仍较易发生感冒，体质较弱的朋友请注意适当防护。
     * forecast : [{"fengxiang":"北风","fengli":"4-5级","high":"高温 23℃","type":"多云","low":"低温 10℃","date":"18日星期二"},{"fengxiang":"北风","fengli":"微风级","high":"高温 20℃","type":"阴","low":"低温 11℃","date":"19日星期三"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 22℃","type":"多云","low":"低温 11℃","date":"20日星期四"},{"fengxiang":"南风","fengli":"微风级","high":"高温 23℃","type":"晴","low":"低温 12℃","date":"21日星期五"},{"fengxiang":"西南风","fengli":"3-4级","high":"高温 27℃","type":"多云","low":"低温 13℃","date":"22日星期六"}]
     * yesterday : {"fl":"3-4级","fx":"西南风","high":"高温 27℃","type":"多云","low":"低温 12℃","date":"17日星期一"}
     * aqi : 65
     * city : 北京
     */

    private String wendu;
    private String ganmao;
    private YesterdayBean yesterday;
    private String aqi;
    private String city;
    private List<ForecastBean> forecast;

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public YesterdayBean getYesterday() {
        return yesterday;
    }

    public void setYesterday(YesterdayBean yesterday) {
        this.yesterday = yesterday;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ForecastBean> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastBean> forecast) {
        this.forecast = forecast;
    }

    public static class YesterdayBean {
        /**
         * fl : 3-4级
         * fx : 西南风
         * high : 高温 27℃
         * type : 多云
         * low : 低温 12℃
         * date : 17日星期一
         */

        private String fl;
        private String fx;
        private String high;
        private String type;
        private String low;
        private String date;

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getFx() {
            return fx;
        }

        public void setFx(String fx) {
            this.fx = fx;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class ForecastBean {
        /**
         * fengxiang : 北风
         * fengli : 4-5级
         * high : 高温 23℃
         * type : 多云
         * low : 低温 10℃
         * date : 18日星期二
         */

        private String fengxiang;
        private String fengli;
        private String high;
        private String type;
        private String low;
        private String date;

        public String getFengxiang() {
            return fengxiang;
        }

        public void setFengxiang(String fengxiang) {
            this.fengxiang = fengxiang;
        }

        public String getFengli() {
            return fengli;
        }

        public void setFengli(String fengli) {
            this.fengli = fengli;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
