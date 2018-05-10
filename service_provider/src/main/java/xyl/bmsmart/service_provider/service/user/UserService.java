package xyl.bmsmart.service_provider.service.user;

import xyl.bmsmart.common.common.model.SCUser;
import xyl.bmsmart.service_provider.service.BaseService;

import java.util.Map;

public interface UserService extends BaseService<SCUser> {
    /**
     * 查询用户
     *
     * @param map
     * @return xyl.bmsmart.common.common.model.SCUser
     * @author XiaYaLing
     * @date 2018/5/9
     */
    SCUser getUser(Map<String, String> map);

    /**
     * 更新用户
     *
     * @param map
     * @return int
     * @author XiaYaLing
     * @date 2018/5/10
     */
    int updateUser(Map<String, String> map);
}
