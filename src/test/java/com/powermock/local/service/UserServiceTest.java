package com.powermock.local.service;

import com.powermock.common.User;
import com.powermock.local.dao.UserDao;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @ClassName UserServiceTest
 * @Description TODO
 * @date 2022/7/20 22:56
 * @Author chen
 */
/**
 * 拓展：当需要mock final方法的时候，必须加@PrepareForTest和@RunWith注解,@PrepareForTest里写的类是final方法所在的类。
 * 当需要mock final方法的时候，必须加@PrepareForTest和@RunWith注解,@PrepareForTest里写的类是final方法所在的类。
 * */
@RunWith(PowerMockRunner.class)
//版本不匹配出错，现版本不报错
@PrepareForTest({UserService.class})
public class UserServiceTest {

    @Test
    public void queryUserCount() {
        try {
            UserService userService = new UserService();
            UserDao userDao = mock(UserDao.class);
            /**
             * withNoArguments,使用的UserDao是无参构造
             * 在局部变量中new UserDao时，使用的时无参构造且返回的是mock出来的UserDao
             * */
            whenNew(UserDao.class).withNoArguments().thenReturn(userDao);
            doReturn(10).when(userDao).getCount();
            int result = userService.queryUserCount();
            assertEquals(10, result);
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    public void saveUser() {
        try {
            User user = new User();
            UserService userService = new UserService();
            UserDao userDao = mock(UserDao.class);
            whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
            doNothing().when(userDao).insertUser(user);
            userService.saveUser(user);
            Mockito.verify(userDao).insertUser(user);
        } catch (Throwable e) {
            fail();
        }
    }
}