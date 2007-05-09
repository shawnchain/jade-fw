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

import java.util.Iterator;

import com.nonsoft.annotation.ConvertResult;
import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.bo.Entity;
import com.nonsoft.discuss.domain.IForum;
import com.nonsoft.persistence.hibernate3.HibernateDAOSupport;

/**
 * <p>
 * </p>
 * 
 * <p>Copyright: Copyright (c) 2003-2006 NonSoft.com</p>
 * 
 * @author Shawn Qian(shawn.chain@gmail.com)
 * @version 2.0, $Id$
 * @since
 */

public class Forum extends Content implements IForum{
    private static final long serialVersionUID = 5645551906286027421L;

    @InjectComponent()
    private HibernateDAOSupport daoSupport;
    public Forum(Entity entity) {
        super(entity);
    }

    @ConvertResult(from=com.nonsoft.bo.Entity.class, to=com.nonsoft.discuss.domain.ITopic.class)
    public Iterator listTopics() {
        return daoSupport.iterate("from TopicEntity t where t.forum.id=" + getId());
    }
}
