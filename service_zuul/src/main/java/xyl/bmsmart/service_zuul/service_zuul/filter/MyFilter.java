package xyl.bmsmart.service_zuul.service_zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import xyl.bmsmart.service_zuul.service_zuul.config.RemoteProperties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @Resource
    RemoteProperties remoteProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        String token = remoteProperties.getToken();

        log.info("token:" + token);

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if (!token.equals(accessToken)) {
            log.info("######### token is error ##########");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
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
