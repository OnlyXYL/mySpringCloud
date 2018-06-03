package top.wikl.web.handler;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import top.wikl.common.exception.CIBaseException;
import top.wikl.common.exception.ExceptionInfo;

import java.io.IOException;

/**
 * 对feign中捕获到的服务中的异常进行封装成 HystrixBadRequestException 使之免除熔断
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/24
 * @return
 */
@Slf4j
public class CustomerErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        CIBaseException exception = null;
        try {
            if (response.body() != null) {
                String body = Util.toString(response.body().asReader());
                log.error(body);
                ExceptionInfo exceptionInfo = this.bodyToExceptionInfo(body, ExceptionInfo.class);

                String message = exceptionInfo.getMessage();
                // 这里只封装4开头的请求异常
                if (400 <= response.status() && response.status() <= 500) {
                    exception = new CIBaseException(message, exception);
                } else {
                    log.error(exception.getMessage(), exception);
                }
            }
        } catch (IOException var4) {
            var4.printStackTrace();
            exception = new CIBaseException("系统运行异常");
        }

        return null != exception ? exception : new CIBaseException("系统运行异常");
    }

    /**
     * @param body
     * @return xyl.bmsmart.service_feign.service_feign.ExceptionInfo
     * @author XiaYaLing
     * @date 2018/5/16
     */
    public ExceptionInfo bodyToExceptionInfo(String body, Class<ExceptionInfo> exceptionInfoClass) {
        JSONObject jsonObject = JSONObject.fromObject(body);//将json字符串转换为json对象
        log.info(jsonObject.toString());
        ExceptionInfo exceptionInfo = (ExceptionInfo) JSONObject.toBean(jsonObject, exceptionInfoClass);//将建json对象转换为Person对象
        return exceptionInfo;
    }
}
