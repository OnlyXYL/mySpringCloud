package xyl.bmsmart.service_provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyl.bmsmart.service_provider.config.RemoteProperties;
import xyl.bmsmart.service_provider.service.HiCloudService;

import javax.annotation.Resource;

@RestController
public class HiCloudController {

    @Resource
    RemoteProperties remoteProperties;

    @Resource
    HiCloudService hiCloudService;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi/{param}")
    public String home(@PathVariable("param") String name) {

        String providerPort = remoteProperties.getServerProviderPort();

        String result = hiCloudService.service8762(name, providerPort);

        return result;
    }


}
