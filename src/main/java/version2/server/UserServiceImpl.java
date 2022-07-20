package version2.server;

import version2.common.User;
import version2.service.UserService;

import java.util.List;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 16:15
 */
public class UserServiceImpl implements UserService {

    /**
     * 暂时只想用这个方法
     * @param name
     * @return
     */
    @Override
    public User getUserByName(String name) {
        //假装去数据库拿了数据
        User build = User.builder().name(name).age(22).gender("man").build();

        return build;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
