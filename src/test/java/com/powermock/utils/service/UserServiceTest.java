package com.powermock.utils.service;

import com.powermock.common.User;
import com.powermock.utils.dao.UserDao;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.*;

/**
 * @ClassName UserServiceTest
 * @Description TODO
 * @date 2022/7/19 23:49
 * @Author chen
 */
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserDao userDao;

    /**
     * 有返回值的测试方法如下
     * */
    @Test
    public void testQueryUserCountWithPowermock() {
        UserDao dao = PowerMockito.mock(UserDao.class);
//        PowerMockito.doReturn(10).when(dao).getCount();
        PowerMockito.when(dao.getCount()).thenReturn(10);
        UserService service = new UserService(dao);
        int result = service.queryUserCount();
        assertEquals(10, result);
    }

    /**
     * 无返回值时的测试方法
     */
    @Test
    public void testSaveUserWithPowermock() {
        UserDao dao = PowerMockito.mock(UserDao.class);
        User user = new User();
        //在执行无返回值的方法时，不抛出异常，doNothing
        PowerMockito.doNothing().when(dao).insertUser(user);
        UserService userService = new UserService(dao);
        userService.saveUser(user);
        //查看dao层中insertUser方法是否调用User，类似于assert
        Mockito.verify(dao).insertUser(user);
    }

    @Ignore
    @Test
    public void testQueryUserCountWithMockito() {
        MockitoAnnotations.initMocks(this);
        UserService service = new UserService(userDao);
        Mockito.when(userDao.getCount()).thenReturn(10);
        int result = service.queryUserCount();
        assertEquals(10, result);
    }

    @Before
    public void setup() {
        userService = new UserService(new UserDao());
    }

    @Ignore
    @Test
    public void queryUserCount() {
        try {
            int count = userService.queryUserCount();
            fail("should not process to here");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    @Ignore
    @Test
    public void saveUser() {
        try {
            userService.saveUser(new User());
            fail("should not process to here");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }
}