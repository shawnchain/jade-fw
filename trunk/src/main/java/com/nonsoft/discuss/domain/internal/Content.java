//==============================================================================
// Created on 2007-5-3
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

package com.nonsoft.discuss.domain.internal;

import java.util.Date;

import com.nonsoft.bo.BusinessObject;
import com.nonsoft.bo.Entity;
import com.nonsoft.discuss.domain.IContent;

/**
 * <p>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2003-2006 NonSoft.com
 * </p>
 * 
 * @author Shawn Qian(shawn.chain@gmail.com)
 * @version 2.0, $Id$
 * @since
 */

public class Content extends BusinessObject implements IContent {

    private static final long serialVersionUID = -8408223892167394234L;

    public Content(Entity entity) {
        super(entity);
    }

    public String getBody() {
        return (String)getEntityProperty("body");
    }

    public Date getCreationDate() {
        return (Date)getEntityProperty("creationDate");
    }

    public Date getModificationDate() {
        return (Date)getEntityProperty("modificationDate");
    }

    public String getTitle() {
        return (String)getEntityProperty("title");
    }

    public Long getId() {
        return getEntity().getId();
    }

}
