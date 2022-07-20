package com.powermock.utils.service;

import com.powermock.common.User;
import com.powermock.utils.dao.UserDao;

/**
 * @ClassName UserService
 * @Description TODO
 * @date 2022/7/19 23:43
 * @Author chen
 */
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int queryUserCount() {
        return this.userDao.getCount();
    }

    public void saveUser(User user){
        userDao.insertUser(user);
    }
}
