package xyl.bmsmart.service_provider_2.service;

import xyl.bmsmart.service_provider_2.model.User;

import java.util.List;

/**
 * @param
 * @author XiaYaLing
 * @date 2018/5/9
 * @return
 */
public interface IUserService {

    User findUserById(String userId);

    List<User> findAllUsers();

    int insertUser(User user);
}