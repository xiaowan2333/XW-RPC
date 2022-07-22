package version4.service;

import version4.common.User;

import java.util.List;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 16:10
 */
public interface UserService {
    User getUserByName(String name);

    List<User> getAll();
}
