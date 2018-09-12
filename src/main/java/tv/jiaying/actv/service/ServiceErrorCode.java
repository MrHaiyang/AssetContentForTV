package tv.jiaying.actv.service;

/**
 * 使用枚举类型来封装异常码和异常信息
 */
public enum ServiceErrorCode {

    OK(0,"ok"),
    WRONG_CHECKCODE(1,"应用校验错误"),
    UNKNOWN_ASSET(10,"未知媒资"),
    UNKNOWN_PROVIDER(20,"未知提供商"),
    UNKNOWN_COLUM(30,"未知栏目"),
    UNKNOWN_COLUM_CONTENTTYPE(33,"获取栏目列表接口参数[contentType]错误"),
    UNKNOWN_COLUM_REPEATNAME(34,"新增栏目的名称重复"),
    UNKNOWN_COLUM_ROOTEXIST(35,"根栏目已经存在，不允许创建根栏目"),
    UNKNOWN_RELEVANCE_WRONGTYPE(40,"错误的关联类型"),
    UNKNOWN_RELEVANCE(41,"关联不存在"),
    UNKNOWN_SYSTEM_USER(51,"用户不存在"),
    UNKNOWN_SYSTEM_USER_REPEATNAME(52,"用户名重复"),
    UNKNOWN_SYSTEM_USER_WRONGPW(53,"密码错误"),
    UNKNOW_COLLECTION(61,"未知收藏信息"),
    UNKNOW_COLLECTION_REPEAT(60,"收藏信息已存在");



    private int code;

    private String msg;

    ServiceErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
