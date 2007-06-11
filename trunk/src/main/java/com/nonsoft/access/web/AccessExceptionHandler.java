//==============================================================================
// Created on 2007-6-10
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

package com.nonsoft.access.web;

import java.net.URLEncoder;

import org.picocontainer.Startable;

import com.nonsoft.access.AccessException;
import com.nonsoft.web.Config;
import com.nonsoft.web.controller.ProcessResult;
import com.nonsoft.web.controller.RequestHandler;
import com.nonsoft.web.controller.RuntimeData;
import com.nonsoft.web.controller.exceptionHandler.ExceptionHandler;

/**
 * <p>
 * </p>
 * 
 * <p>Copyright: Copyright (c) 2003-2006 NonSoft.com</p>
 * 
 * @author Shawn Qian
 * @version 2.0, $Id$
 * @since
 */

public class AccessExceptionHandler extends ExceptionHandler implements Startable{
    private RequestHandler requestHandler;
    private Config config;
    private String loginPage = "/login.htm";
    
    public AccessExceptionHandler() {
        super(AccessException.class);
    }

    public ProcessResult process(RuntimeData rd) throws Throwable {
        String targetPage = rd.getRequest().getRequestURI();
        
        //FIXME collect the parameters and handle the POST parameters ?
        String encodedTarget = URLEncoder.encode(targetPage,config.getCharacterEncoding());
        String redirect = rd.getRequest().getContextPath() + loginPage + "?target=" + encodedTarget;
        rd.getResponse().sendRedirect(redirect);
        
        return ProcessResult.BREAK_PROCESS;
    }

    public void start() {
        // Register self into the exception handler list
        requestHandler.registerExceptonHandler(this);
    }

    public void stop() {
        requestHandler.unregisterExceptionHandler(this);
    }
}
