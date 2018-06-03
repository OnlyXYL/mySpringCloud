package top.wikl.web.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wikl.common.model.JsonResult;
import top.wikl.web.service.user.FeignUserService;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    FeignUserService feignUserService;

    @RequestMapping(value = "/{param}")
    public String getUser(@PathVariable("param") String param) {

        JsonResult jsonResult = new JsonResult();
        try {
            String userByParm = feignUserService.getUserByParm(param);
            jsonResult.success("操作成功",userByParm);
        } catch (Exception e) {
            log.info("Exception:\ncause," + e.getCause() + "\nmessage," + e.getMessage());
            jsonResult.error(e.getMessage(), null);
            return jsonResult.toString();
        }
        return jsonResult.toString();
    }
}
