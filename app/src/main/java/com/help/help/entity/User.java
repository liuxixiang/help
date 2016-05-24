package com.help.help.entity;

/**
 * Created by Administrator on 2016/3/28.
 */
public class User {

    /**
     * code : 1
     * result : {"name":"adley3","id":4,"status":null,"mobile":"adley3","storeId":null,"email":null,"nickName":null,"idCard":null,"createTime":1458740599000,"updateTime":null,"account":"adley3","password":"123456"}
     * msg : null
     */

    private int code;
    /**
     * name : adley3
     * id : 4
     * status : null
     * mobile : adley3
     * storeId : null
     * email : null
     * nickName : null
     * idCard : null
     * createTime : 1458740599000
     * updateTime : null
     * account : adley3
     * password : 123456
     */

    private ResultBean result;
    private Object msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public static class ResultBean {
        private String name;
        private int id;
        private Object status;
        private String mobile;
        private Object storeId;
        private Object email;
        private Object nickName;
        private Object idCard;
        private long createTime;
        private Object updateTime;
        private String account;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getStoreId() {
            return storeId;
        }

        public void setStoreId(Object storeId) {
            this.storeId = storeId;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getNickName() {
            return nickName;
        }

        public void setNickName(Object nickName) {
            this.nickName = nickName;
        }

        public Object getIdCard() {
            return idCard;
        }

        public void setIdCard(Object idCard) {
            this.idCard = idCard;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
