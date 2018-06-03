package top.wikl.service_provider.service;

import org.springframework.stereotype.Service;

@Service
public class HiCloudService {

    public String service8762(String name, String port) {
        return "hi " + name + ",i am from port:" + port;
    }

}
