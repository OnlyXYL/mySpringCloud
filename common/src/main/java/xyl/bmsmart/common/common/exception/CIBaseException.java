package xyl.bmsmart.common.common.exception;

public class CIBaseException extends RuntimeException {
    public static final int UNKNOWN_EXCEPTION = 0;
    public static final int BIZ_EXCEPTION = 1;
    public static final int TIMEOUT_EXCEPTION = 2;
    public static final int FORBIDDEN_EXCEPTION = 3;
    public static final int CISYS_EXCEPTION = 4;

    private int typecode = BIZ_EXCEPTION;// CIBaseException，异常类型用tpecode表示
    private String errCode;

    public CIBaseException() {
        super();
    }

    public CIBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CIBaseException(String message) {
        super(message);
    }

    public CIBaseException(Throwable cause) {
        super(cause);
    }


    public CIBaseException(int type, String errCode) {
        super();
        this.typecode = type;
        this.errCode = errCode;
    }

    public CIBaseException(int type, String errCode, String message, Throwable cause) {
        super(message, cause);
        this.typecode = type;
        this.errCode = errCode;
    }

    public CIBaseException(int type, String errCode, String message) {
        super(message);
        this.typecode = type;
        this.errCode = errCode;
    }

    public CIBaseException(int type, String errCode, Throwable cause) {
        super(cause);
        this.typecode = type;
        this.errCode = errCode;
    }

    public int getTypecode() {
        return typecode;
    }

    public void setTypecode(int typecode) {
        this.typecode = typecode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}