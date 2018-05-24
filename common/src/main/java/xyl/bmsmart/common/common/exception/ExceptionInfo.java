package xyl.bmsmart.common.common.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 服务中异常信息封装对象
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/24
 * @return
 */
@Slf4j
@Data
public class ExceptionInfo implements Serializable {

    private String timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

}
