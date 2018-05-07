package xyl.bmsmart.service_zuul.service_zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.WebUtils;
import xyl.bmsmart.service_zuul.service_zuul.config.RemoteProperties;
import xyl.bmsmart.service_zuul.service_zuul.exception.TokenException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

/**
 * zuul服务过滤
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/2
 * @return
 */

/**
 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
 * pre：路由之前
 * routing：路由之时
 * post： 路由之后
 * error：发送错误调用
 * filterOrder：过滤的顺序
 * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
 * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
 */
@Component
@Slf4j
public class MyFilter extends ZuulFilter {

    private static final String RESPONSE_KEY_TOKEN = "token";
    @Value("${system.config.authFilter.authUrl}")
    private String authUrl;
    @Value("${system.config.authFilter.tokenKey}")
    private String tokenKey = RESPONSE_KEY_TOKEN;

    @Resource
    RemoteProperties remoteProperties;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;

    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        /**
         * 过滤条件,满足条件才会执行过滤，可用于对需要登陆的服务请求进行过滤，判断是否登陆
         */
        RequestContext context = getCurrentContext();
        String url = context.getRequest().getRequestURI().toString();
        boolean contains = authUrl.contains(context.getRequest().getRequestURI().toString());
        if (contains) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object run() throws ZuulException {

        String token = remoteProperties.getToken();

        log.info("token:" + token);

        RequestContext ctx = getCurrentContext();

        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if (!token.equals(accessToken)) {
//            throw new IllegalArgumentException("token is error");
            log.info("######### token is error ##########");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
//            ctx.setResponseBody("{\"result\":\"token is error!\"}");
//            ctx.setThrowable(new Throwable("token is error"));
            try {
                ctx.getResponse().getWriter().write("######### token is error ##########");
            } catch (Exception e) {
            }

            return null;
        }
        log.info("######### ok ##########");
        return null;

    }
}
