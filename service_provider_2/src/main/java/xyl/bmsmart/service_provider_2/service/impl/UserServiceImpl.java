package xyl.bmsmart.service_provider_2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyl.bmsmart.service_provider_2.mapper.user.UserMapper;
import xyl.bmsmart.service_provider_2.model.User;
import xyl.bmsmart.service_provider_2.service.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}