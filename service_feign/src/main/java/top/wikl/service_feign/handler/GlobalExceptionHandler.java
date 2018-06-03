package top.wikl.service_feign.handler;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.wikl.common.exception.CIBaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 全局异常处理
 *
 * @author XiaYaLing
 * @date 2018/4/25
 * @param
 * @return
 */

/**
 * ControllerAdvice, 表示 GlobalExceptionHandler 是一个全局的异常处理器.
 * 需要注意的是, ExceptionHandler 的优先级比 ControllerAdvice 高, 即 Controller 抛出的异常如果既可以让 ExceptionHandler 标注的方法处理,
 * 又可以让 ControllerAdvice 标注的类中的方法处理, 则优先让 ExceptionHandler 标注的方法处理.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CIBaseException.class)
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        e.printStackTrace();

        Throwable rex = e;

        if (e instanceof HystrixRuntimeException) {
            rex = e.getCause();
        }

        if (rex instanceof CIBaseException) {

       CIBaseException cbe = (CIBaseException) rex;
            String message = cbe.getMessage();
            log.info(message);
               /*  if (StringUtils.isBlank(cbe.getErrCode())
                    && cbe.getMessage().matches(this.errCodePattern)) {
                String[] cm = this.parseCodeMessage(cbe.getMessage());
                this.writeError(cm[0], cm[1], httpServletResponse);
            } else {
                this.writeError(cbe.getErrCode(), rex.getMessage(), httpServletResponse);
            }
*/
        } else {
//            this.writeError("", "系统运行出错", httpServletResponse);
        }

        return new ModelAndView();
    }

}