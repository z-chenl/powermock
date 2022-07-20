package com.powermock.local.service;

import com.powermock.common.User;
import com.powermock.local.dao.UserDao;

/**
 * @ClassName UserService
 * @Description TODO
 * @date 2022/7/19 23:43
 * @Author chen
 */
public class UserService {

    public int queryUserCount() {
        UserDao userDao = new UserDao();
        return userDao.getCount();
    }

    /**
     * 没有返回值 void
     * 测试是不可以使用assert断言
     */
    public void saveUser(User user) {
        UserDao userDao = new UserDao();
        userDao.insertUser(user);
    }
}
