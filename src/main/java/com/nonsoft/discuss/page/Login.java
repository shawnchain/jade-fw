//==============================================================================
// Created on 2007-6-1
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

package com.nonsoft.discuss.page;

import com.nonsoft.access.authentication.AuthenticationException;
import com.nonsoft.access.web.AccessService;
import com.nonsoft.annotation.Inject;
import com.nonsoft.annotation.Parameter;
import com.nonsoft.web.action.ActionTarget;
import com.nonsoft.web.controller.RuntimeData;
import com.nonsoft.web.form.Form;
import com.nonsoft.web.view.Page;

/**
 * <p>
 * </p>
 * 
 * <p>Copyright: Copyright (c) 2003-2006 NonSoft.com</p>
 * 
 * @author Shawn Qian
 * @version 2.0, $Id$
 * @since 1.0
 * 
 */

@Parameter(rootObject="runtime",expr="request.param")
public class Login extends Page {
    
    @Inject()
    private AccessService authService;
    
    @Parameter() // $request.parameters.logout
    private String logout;
    
    @Parameter() // $request.parameters.target
    private String target = "/";
    
    @Override
    public ActionTarget execute(RuntimeData runtimeData) throws Throwable {
        
        //If user has already logged in, skip the authentication process
        if(authService.isAuthenticated()){
            // Is logout ?
            if(logout != null){
                authService.doLogout();
            }
            return ActionTarget.redirect(target/*"/index.jsp"*/);
        }
        
        // Validate form data
        Form form = runtimeData.getForm();
        if(form == null){
            return null;
        }
        if(!form.isValid()){
            getContext().put("error", "Invalid input");
            return null;
        }
        
        // Do auth
        String username = (String)form.getField("username").getValue();
        String password = (String)form.getField("password").getValue();
        try{
            authService.doAuth(username, password);
            return ActionTarget.redirect(target);
        }catch(AuthenticationException ae){
            getContext().put("error", ae.getReason());
        }
        return null;
    }
    
    @Override
    public void render() throws Throwable {
        // 3 possible states. a. first time be here; b. incorrect input; c. login failed
    }

}
