package xyl.bmsmart.web.web.exception;


import xyl.bmsmart.common.common.model.JsonResult;

/**
 * 自定义系统异常
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/25
 * @return
 */
public class SystemException extends Exception {
    private JsonResult jsonResult;

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setScmessage(JsonResult scmessage) {
        this.jsonResult = scmessage;
    }

    public SystemException() {
        super();
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(JsonResult jsonResult) {
        super(jsonResult.getContent());
        this.jsonResult = jsonResult;
    }
}
