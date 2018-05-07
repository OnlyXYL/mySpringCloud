package xyl.bmsmart.service_zuul.service_zuul.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认的异常处理controller
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/4
 * @return
 */
//@RestController
public class ErrorHandlerController implements ErrorController {
    /**
     * 出异常后进入该方法，交由下面的方法处理
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {

        String message = request.getAttribute("TOKEN_ERROR").toString();

        return message;
    }
}
