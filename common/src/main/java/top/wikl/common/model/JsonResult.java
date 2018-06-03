package top.wikl.common.model;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 消息
 */
public class JsonResult implements java.io.Serializable {

    /**
     * 类型
     */
    public enum JsonResultType {

        /**
         * 成功
         */
        success,

        /**
         * 警告
         */
        warn,

        /**
         * 错误
         */
        error,
    }

    /**
     * 类型
     */
    private JsonResultType jsonResultType;

    /**
     * 内容
     */
    private String content;

    /**
     * 数据
     */
    private Object data;

    /**
     * 初始化一个新创建的 JsonResult 对象，使其表示一个空消息。
     */
    public JsonResult() {

    }

    /**
     * 初始化一个新创建的 JsonResult 对象
     *
     * @param type    类型
     * @param content 内容
     */
    public JsonResult(JsonResultType type, String content) {
        this.jsonResultType = type;
        this.content = content;
    }

    public JsonResult(JsonResultType type, String content, Object data) {
        super();
        this.jsonResultType = type;
        this.content = content;
        this.data = data;
    }


    /**
     * 返回成功消息
     *
     * @param content 内容
     * @param data    返回前台数据
     * @return 成功消息
     */
    public void success(String content, Object data) {
        this.jsonResultType = JsonResult.JsonResultType.success;
        this.content = content;
        this.data = data;
    }

    /**
     * 返回警告消息
     *
     * @param content 内容
     * @param data    返回前台数据
     * @return 警告消息
     */
    public void warn(String content, Object data) {
        this.jsonResultType = JsonResult.JsonResultType.warn;
        this.content = content;
        this.data = data;
    }

    /**
     * 返回错误消息
     *
     * @param content 内容
     * @param data    返回前台数据
     * @return 错误消息
     */
    public void error(String content, Object data) {
        this.jsonResultType = JsonResult.JsonResultType.error;
        this.content = content;
        this.data = data;
    }

    /**
     * 获取类型
     *
     * @return 类型
     */
    public JsonResultType getType() {
        return jsonResultType;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(JsonResultType type) {
        this.jsonResultType = type;
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取 data
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置 data
     *
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public String toString() {

        //存在重复的数据在json转化时候的问题（叫循环引用检测）
//        return JSONUtils.toJSONString(this);
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

}