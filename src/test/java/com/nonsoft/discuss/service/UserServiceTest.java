
package com.nonsoft.discuss.service;

import test.WebTestCase;

import com.nonsoft.discuss.domain.User;
import com.nonsoft.persistence.ISessionManager;

public class UserServiceTest extends WebTestCase {

    public void testLoadUserLong() throws Exception{
        ISessionManager sm = (ISessionManager) container.getComponentInstance(ISessionManager.class);
        try {
            sm.beginTransaction();
            UserService us = (UserService)container.getComponentInstance(UserService.class);
            //IUser user = us.loadUser(new Long(1));
            User user = us.loadUser(new Long(1));
            assertNotNull(user);
            System.out.println(user.getEmail());
            assertEquals(new Long(1), user.getId());
        } catch (Exception e) {
            throw e;
        } finally {
            sm.endTransaction();
        }
    }

    public void testLoadUserString() throws Exception{
        ISessionManager sm = (ISessionManager) container.getComponentInstance(ISessionManager.class);
        try {
            sm.beginTransaction();
            UserService us = (UserService)container.getComponentInstance(UserService.class);
            User user = us.findUserByToken("admin");
            assertNotNull(user);
            assertEquals(new Long(1), user.getId());
            assertEquals("admin", user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sm.endTransaction();
        }
    }

}
