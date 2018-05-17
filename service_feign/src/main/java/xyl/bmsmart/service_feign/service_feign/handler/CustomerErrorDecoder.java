package xyl.bmsmart.service_feign.service_feign.handler;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import xyl.bmsmart.common.common.exception.CIBaseException;
import xyl.bmsmart.service_feign.service_feign.ExceptionInfo;

import java.io.IOException;

@Slf4j
public class CustomerErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.info("+++++++++++++ErrorDecoder+++++++++++");
        Exception ex = null;
        try {
            if (response.body() != null) {
                String body = Util.toString(response.body().asReader());

                ExceptionInfo exceptionInfo = this.bodyToExceptionInfo(body, ExceptionInfo.class);

                //异常信息
                String message = exceptionInfo.getMessage();

                //异常编码
                String error = exceptionInfo.getError();

//                ex = new CIBaseException(CIBaseException.BIZ_EXCEPTION, error, message);
                ex =  new HystrixBadRequestException(message);
            }
        } catch (IOException var4) {
            var4.printStackTrace();
            ex = new CIBaseException(CIBaseException.UNKNOWN_EXCEPTION, "", "系统运行异常");
        }

        return null != ex ? ex : new HystrixBadRequestException("系统运行异常");

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
