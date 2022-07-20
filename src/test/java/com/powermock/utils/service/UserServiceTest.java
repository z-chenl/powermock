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

import javax.management.OperationsException;

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

    @Test
    public void testWithPowermock() {
        UserDao dao = PowerMockito.mock(UserDao.class);
//        PowerMockito.doReturn(10).when(dao).getCount();
        PowerMockito.when(dao.getCount()).thenReturn(10);
        UserService service = new UserService(dao);
        int result = service.queryUserCount();
        assertEquals(10, result);
    }

    @Ignore
    @Test
    public void testWithMockito() {
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