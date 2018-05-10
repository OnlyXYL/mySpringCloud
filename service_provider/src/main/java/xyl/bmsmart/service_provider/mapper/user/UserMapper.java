package xyl.bmsmart.service_provider.mapper.user;


import xyl.bmsmart.common.common.model.SCUser;
import xyl.bmsmart.service_provider.mapper.BaseMapper;

import java.util.Map;

public interface UserMapper extends BaseMapper<SCUser> {

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
