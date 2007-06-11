
package com.nonsoft.access;

import com.nonsoft.access.authentication.AuthenticationException;
import com.nonsoft.access.authentication.CryptAuthenticationService;
import com.nonsoft.access.authentication.MemoryPasswordStore;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * </p>
 * 
 * <p>Copyright: Copyright (c) 2003-2006 NonSoft.com</p>
 * 
 * @author Shawn Qian
 * @version 2.0, $Id$
 * @since
 */
public class CryptAuthenticationServiceTest extends TestCase {

    public void testAuthenticate() {
        MemoryPasswordStore ps = new MemoryPasswordStore();
        ps.addPassword("root", "$apr1$rf4.....$.LbV5qxK0Vu/9h/QTCKlw0");
        CryptAuthenticationService as = new CryptAuthenticationService(ps);
        
        try {
            as.authenticate("root", "badpassword");
            fail("Should fail");
        } catch (AuthenticationException e) {
            System.out.println("Got expected exception " + e);
        }
        
        try {
            as.authenticate("root", "secret");
        } catch (AuthenticationException e) {
            fail(e.getMessage());
        }
    }

//    public void testChangePassword() {
//        fail("Not yet implemented");
//    }
}
