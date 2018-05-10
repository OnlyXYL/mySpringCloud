package xyl.bmsmart.service_provider.service.Impl.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyl.bmsmart.common.common.model.SCUser;
import xyl.bmsmart.service_provider.mapper.user.UserMapper;
import xyl.bmsmart.service_provider.service.Impl.BaseServiceImpl;
import xyl.bmsmart.service_provider.service.user.UserService;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<UserMapper, SCUser> implements UserService {
    /**
     * 查询用户
     *
     * @param map
     * @return xyl.bmsmart.common.common.model.SCUser
     * @author XiaYaLing
     * @date 2018/5/9
     */
    @Override
    public SCUser getUser(Map<String, String> map) {

        log.info(":::inter " + this.getClass().getName() + ":::方法:::" + Thread.currentThread().getStackTrace()[1].getMethodName() + ":::参数:::map," + map.toString());
        return baseMapper.getUser(map);
    }

    /**
     * 更新用户
     *
     * @param map
     * @return int
     * @author XiaYaLing
     * @date 2018/5/10
     */
    @Override
    public int updateUser(Map<String, String> map) {
        return baseMapper.updateUser(map);
    }
}
