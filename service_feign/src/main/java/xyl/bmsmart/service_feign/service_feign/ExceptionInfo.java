package xyl.bmsmart.service_feign.service_feign;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class ExceptionInfo implements Serializable{

    private String timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

}
