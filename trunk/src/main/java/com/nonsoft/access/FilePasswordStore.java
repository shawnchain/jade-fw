//==============================================================================
// Created on 2007-6-6
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.picocontainer.Startable;

import com.nonsoft.aop.util.SystemUtils;

/**
 * 
 * <p>
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
public class FilePasswordStore implements IPasswordStore, Startable {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FilePasswordStore.class);

    // private String passwordFilePath = SystemUtils.USER_DIR + "/passwd";
    private String passwordFilePath = SystemUtils.USER_DIR + "/src/test/resources/passwd.txt";

    private Boolean readOnly = Boolean.FALSE;

    private HashMap<String, String> passwordStore = new HashMap<String, String>();

    public String getPassword(String username) {
        return passwordStore.get(username);
    }

    public String getPasswordFilePath() {
        return passwordFilePath;
    }

    public void setPasswordFilePath(String passwordFilePath) {
        this.passwordFilePath = passwordFilePath;
    }

    public void updatePassword(String username, String password) {
        if (readOnly) {
            throw new UnsupportedOperationException("Password store is read-only");
        }

        // TODO implement updatePassword() to save to the file
        passwordStore.put(username, password);
        return;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    private void loadPassword() {
        File file = new File(passwordFilePath);

        if (!file.exists() || !file.canRead()) {
            logger.warn("Password file '" + passwordFilePath + "' not found");
            return;
        }

        if (!file.canWrite()) {
            readOnly = Boolean.TRUE;
        }

        // Password file found, try to load it!
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            do {
                String aline = reader.readLine();
                if (aline == null) {
                    break;
                }
                String[] sArray = aline.trim().split("[\\s:]+");
                if (sArray.length >= 2) {
                    // what if contains domain field ?
                    String username = sArray[0];
                    String password = sArray[1];
                    passwordStore.put(username, password);
                } else {
                    logger.warn("Invalid password entry: " + aline);
                }
            } while (true);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException ioe) {
            // TODO Auto-generated catch block
            ioe.printStackTrace();
        }
    }

    public void start() {
        loadPassword();
    }

    public void stop() {
        passwordStore.clear();
    }

}
