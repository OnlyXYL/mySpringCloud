package xyl.bmsmart.web.web.handler;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import xyl.bmsmart.common.common.exception.CIBaseException;

import java.io.IOException;

@Slf4j
public class CustomerErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        CIBaseException ex = null;
        try {
            if(response.body() != null) {
                String body = Util.toString(response.body().asReader());
                log.error(body);
               /* ExceptionInfo ei = this.objectMapper.readValue(body.getBytes("UTF-8"), ExceptionInfo.class);

                String message = ei.getMessage();
                String code = "";

                if (message.matches(this.errCodePattern)) {
                    String [] cm = this.parseCodeMessage(message);
                    code = cm[0];
                    message = cm[1];
                    ex = new CIBaseException(CIBaseException.BIZ_EXCEPTION, code, message);
                }*/
                ex = new CIBaseException(CIBaseException.BIZ_EXCEPTION, "000", "测试抛出异常");
            }
        } catch (IOException var4) {
            var4.printStackTrace();
            ex = new CIBaseException(CIBaseException.UNKNOWN_EXCEPTION, "", "系统运行异常");
        }

        return null != ex ? ex : new CIBaseException(CIBaseException.UNKNOWN_EXCEPTION, "", "系统运行异常");

    }
}
