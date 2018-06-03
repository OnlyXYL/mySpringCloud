package top.wikl.common.exception;

import top.wikl.common.model.JsonResult;

public class ErrorPageException extends RuntimeException {
    private JsonResult jsonResult;

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setScmessage(JsonResult scmessage) {
        this.jsonResult = scmessage;
    }

    public ErrorPageException() {
        super();
    }

    public ErrorPageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorPageException(String message) {
        super(message);
    }

    public ErrorPageException(Throwable cause) {
        super(cause);
    }

    public ErrorPageException(JsonResult jsonResult) {
        super(jsonResult.getContent());
        this.jsonResult = jsonResult;
    }
}
