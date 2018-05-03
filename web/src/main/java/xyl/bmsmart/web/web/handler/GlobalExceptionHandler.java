package xyl.bmsmart.web.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyl.bmsmart.web.web.exception.BusinessException;
import xyl.bmsmart.web.web.exception.SystemException;

import javax.servlet.http.HttpServletRequest;
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

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Object businessErrorHandler(HttpServletRequest req, BusinessException e) throws Exception {
        log.debug("---BusinessException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return e.getJsonResult();
    }

    @ExceptionHandler(value = SystemException.class)
    @ResponseBody
    public Object systemErrorHandler(HttpServletRequest req, SystemException e) throws Exception {
        log.debug("---SystemException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return e.getJsonResult();
    }
}