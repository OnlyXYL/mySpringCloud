package top.wikl.common.exception;


import top.wikl.common.model.JsonResult;

/**
 * 自定义业务异常
 *
 * @param
 * @author XiaYaLing
 * @date 2018/4/25
 * @return
 */
public class BusinessException extends RuntimeException {

    private JsonResult jsonResult;

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(JsonResult jsonResult) {
        super(jsonResult.getContent());
        this.jsonResult = jsonResult;
    }
}
