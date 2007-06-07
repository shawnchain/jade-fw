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

package com.nonsoft.discuss.domain;

import com.nonsoft.discuss.domain.internal.DiscussDomainObject;
import com.nonsoft.domain.Entity;

/**
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

public class User extends DiscussDomainObject{
    public User(Entity entity) {
        super(entity);
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        Entity e = getEntity();
        if (e != null) {
            return e.getId();
        }
        return null;
    }

    public String getName() {
        return (String) this.getEntityProperty("name");
    }

    public String getEmail() {
        return (String) this.getEntityProperty("email");
    }

    /**
     * Here we'll use email as the user identification
     */
    public String getIdentification() {
        return (String) this.getEntityProperty("email");
    }

}
