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

package com.nonsoft.discuss.service;

import java.util.Iterator;
import java.util.List;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.discuss.domain.User;
import com.nonsoft.discuss.entity.UserEntity;
import com.nonsoft.domain.Entity;
import com.nonsoft.ioc.IContainer;
import com.nonsoft.persistence.hibernate3.HibernateDAOSupport;
import com.nonsoft.persistence.hibernate3.HibernateOperations;

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

public class UserService {
    @InjectComponent()
    private IContainer container;

    @InjectComponent()
    private HibernateDAOSupport daoSupport;
    
    /**
     * Load user by id
     * @param id
     * @return
     */
    public User loadUser(Long id){
        //FIXME userEntity is lazy-loaded so we should use "eager fetch strategy" here
        Entity entity = (Entity) daoSupport.loadEntity(UserEntity.class, id);
        if (entity == null) {
            return null;
        }
        return (User) container.getComponentInstance(User.class, new Class[] { Entity.class },
                new Object[] { entity });
    }
    
    /**
     * Load user by token. In this system, we use email as the unique token
     * @param token
     * @return
     */
    public User findUserByToken(String token){
        //FIXME userEntity is lazy-loaded so we should use "eager fetch strategy" here
        Iterator i = ((List)daoSupport.execute(HibernateOperations.list("from UserEntity u where u.email=?", token))).iterator();
        if(i.hasNext()){
            Entity entity = (Entity)i.next();
            if (entity == null || entity.getId() == null) {
                return null;
            }
            return (User) container.getComponentInstance(User.class, new Class[] { Entity.class },
                    new Object[] { entity });
        }
        return null;
    }
    
}
