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

import com.nonsoft.access.AccessException;
import com.nonsoft.access.web.AccessService;
import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.service.UserService;
import com.nonsoft.web.action.ActionTarget;
import com.nonsoft.web.controller.RuntimeData;
import com.nonsoft.web.view.Page;

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

public class Profile extends Page {
    //private static final String SESSION_KEY = "__user_id__";
    
    @InjectComponent()
    private AccessService accessService;
    
    @InjectComponent()
    private UserService userService;
    
//    @Parameter(expression="request.param.logout",context="runtime", isMust=false)
//    private String logout;
    
    @Override
    @Transactional
    public ActionTarget execute(RuntimeData runtimeData) throws Throwable {
        //FIXME this logic could be done by a SecurityProcessor
        // or customized exception handler
        String token = (String)accessService.getAuthToken();
        if(token == null){
            // user is not authenticated, redirect to the login page
            //return ActionTarget.redirect("/login.htm");
            throw new AccessException();
        }
        
//        
//        //If user has already logged in, skip the authentication process
//        if(authService.isAuthenticated()){
//            // Is logout ?
//            if(logout != null){
//                authService.doLogout();
//            }
//            return ActionTarget.redirect("/index.jsp");
//        }
//        
//        // Validate form data
//        Form form = runtime.getForm();
//        if(form == null){
//            return null;
//        }
//        if(!form.isValid()){
//            getContext().put("error", "Invalid input");
//            return null;
//        }
//        
//        // Do auth
//        String username = (String)form.getField("username").getValue();
//        String password = (String)form.getField("password").getValue();
//        try{
//            authService.doAuth(username, password);
//            return ActionTarget.redirect("/index.jsp");
//        }catch(AuthenticationException ae){
//            getContext().put("error", ae.getFailReason());
//        }
        return null;
    }
    
    @Override
    @Transactional()
    public void render() throws Throwable {
        // Load the user object and store it into context
        String token = (String)accessService.getAuthToken();
        getContext().put("user", userService.findUserByToken(token));

//        Group group = (Group)Group.load(new Long(1));
//        getContext().put("group", group);

    }

}
