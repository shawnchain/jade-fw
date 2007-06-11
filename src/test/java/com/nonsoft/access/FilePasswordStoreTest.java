
package com.nonsoft.access;

import com.nonsoft.access.authentication.FilePasswordStore;

import junit.framework.TestCase;

public class FilePasswordStoreTest extends TestCase {

    public void testGetPassword() {
        FilePasswordStore fps = new FilePasswordStore();
        fps.setPasswordFilePath("src/test/resources/passwd.txt");
        fps.start();
        String p = fps.getPassword("admin");
        assertNotNull(p);
        assertEquals("$apr1$rf4.....$.LbV5qxK0Vu/9h/QTCKlw0",p);
        System.out.println(p);
    }

//    public void testUpdatePassword() {
//        //fail("Not yet implemented");
//        System.out.println(SystemUtils.USER_DIR);
//    }
}
