package tv.jiaying.actv.controller.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tv.jiaying.actv.service.ServiceErrorCode;

public class ResultBean {

    private int status;

    private String msg;

    private Object data;

    public ResultBean() {

    }

    public ResultBean(int status) {
        this(status, "");
    }

    public ResultBean(int status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data=null;

    }

    public ResultBean(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(ServiceErrorCode errorCode){
        this.status = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data=null;
    }

    public ResultBean(ServiceErrorCode errorCode,Object data){
        this.status = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
