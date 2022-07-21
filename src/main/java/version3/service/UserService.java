package version3.service;

import version3.common.User;

import java.util.List;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 16:10
 */
public interface UserService {
    User getUserByName(String name);

    List<User> getAll();
}
