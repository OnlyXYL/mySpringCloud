package top.wikl.common.exception;

import top.wikl.common.model.JsonResult;

public class TokenException extends RuntimeException {
    private JsonResult jsonResult;

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setScmessage(JsonResult scmessage) {
        this.jsonResult = scmessage;
    }

    public TokenException() {
        super();
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }

    public TokenException(JsonResult jsonResult) {
        super(jsonResult.getContent());
        this.jsonResult = jsonResult;
    }
}
