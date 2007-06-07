//==============================================================================
// Created on 2007-5-31
// $Id$
//==============================================================================
//  Copyright (C) <2006,2007>  Shawn Qian, shawn.chain@gmail.com
//
//  This library is free software; you can redistribute it and/or
//  modify it under the terms of the GNU Lesser General Public
//  License as published by the Free Software Foundation; either
//  version 2.1 of the License, or (at your option) any later version.
//
//  This library is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//  Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public
//  License along with this library; if not, write to the Free Software
//  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//==============================================================================

package com.nonsoft.access;

import org.apache.log4j.Logger;

import com.nonsoft.access.util.MD5Crypt;
import com.nonsoft.annotation.InjectComponent;

/**
 * <p>
 * Authenticate and generates the same password the as crypt() under unix<br/>
 * See <a href="http://www.ibm.com/developerworks/cn/linux/l-md5crypt/">http://www.ibm.com/developerworks/cn/linux/l-md5crypt/</a>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2003-2006 NonSoft.com
 * </p>
 * 
 * @author Shawn Qian
 * @version 2.0, $Id$
 * @since
 */

public class CryptAuthenticationService implements AuthenticationService {
    
    private static final Logger logger = Logger.getLogger(CryptAuthenticationService.class);
      
    // Configurable style
    private String style = APACHE_STYLE;
    
    @InjectComponent()
    private IPasswordStore passwordStore;
    
    public CryptAuthenticationService(){}
    
    public CryptAuthenticationService(IPasswordStore pstore){
        this.passwordStore = pstore;
    }

    public synchronized void authenticate(String username, String password) throws AuthException {
        String expectedHash = passwordStore.getPassword(username);
        if(expectedHash == null){
            throw new AuthException("No such user: " + username);
        }
        
        String passwordHash;
        String salt = getSalt(expectedHash.trim());
        String magic= getMagic();
        if(password != null){
            passwordHash = MD5Crypt.crypt(password, salt, magic);   
        }else{
            // password is empty, this should not happen indeed
            passwordHash = MD5Crypt.crypt("", salt, magic);
        }
        
        if(!passwordHash.equals(expectedHash)){
            throw new AuthException("Password does not match!");
        }
        
        //go.. go.. go.., ole..ole...ole...
    }
    
    public boolean changePassword(String username, String oldPassword, String newPassword) throws AuthException{
        // Check against the original password first
        authenticate(username,oldPassword);
        passwordStore.updatePassword(username, newPassword);
        logger.info("User " + username + "'s password updated successfully");
        return true;
    }
    
    private static final String APACHE_STYLE = "APACHE";
    private static final String UNIX_STYLE = "UNIX";
    private static final String APACHE_STYLE_MAGIC = "$apr1$";
    private static final String UNIX_STYLE_MAGIC = "$1$";
    /**
     * Get magic string according to different type.
     * For Linux/BSD, it's $1$ and for Apache, it's $apr1$
     * @return
     */
    private String getMagic(){
        if(style.equalsIgnoreCase(UNIX_STYLE)){
            return UNIX_STYLE_MAGIC;
        }else{
            return APACHE_STYLE_MAGIC;
        }
    }
    

    private static final String DEFAULT_SALT = "9x9x9x9x";
    /**
     * Fragile logic to extract salt from the password hash string
     * @param hash
     * @return
     */
    private String getSalt(String hash){
        String[] s = hash.split("[\\$]{1}");
        if(s.length >= 3){
            return s[2];
        }
        return DEFAULT_SALT;
    }
}
