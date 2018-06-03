package top.wikl.service_provider_2.mapper.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.wikl.service_provider_2.model.User;

import java.util.List;

/**
 * 用户 mybatis 映射文件。
 *
 * @param
 * @author XiaYaLing
 * @date 2018/5/9
 * @return
 */
public interface UserMapper {

    @Select("select * from scUser where userId = #{userId}")
    User findUserById(String userId);

    @Select("select * from scUser")
    List<User> findAllUsers();

    @Insert("INSERT INTO user(username, name, age, balance) VALUES(#{username}, #{name}, #{age}, #{balance})")
    int insertUser(User user);

    @Update("update scUser set name = #{name} where userId = #{userId}")
    int updataUser(User user);
}