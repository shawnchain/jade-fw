//==============================================================================
// Created on 2007-7-13
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

package com.nonsoft.discuss.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.nonsoft.access.AccessException;
import com.nonsoft.access.web.AccessService;
import com.nonsoft.annotation.Inject;

/**
 * <p>
 * Security check interceptor
 * </p>
 * 
 * <p>Copyright: Copyright (c) 2003-2006 NonSoft.com</p>
 * 
 * @author Shawn Qian
 * @version 2.0, $Id$
 * @since
 * @see
 */

public class SecurityCheckInterceptor implements MethodInterceptor {
    @Inject
    private AccessService accessService;
    
    public SecurityCheckInterceptor(){
        System.err.println("" + this + " constructed");
    }
    
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //FIXME should delegate to the authorization service to check whether 
        // current principal is allowd to access the target resource
        if(!accessService.isAuthenticated()){
            throw new AccessException("Not authenticated yet");
        }
        return invocation.proceed();
    }
}
