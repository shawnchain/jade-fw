package com.nonsoft.discuss.domain.internal;


import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.domain.AbstractDomainObject;
import com.nonsoft.domain.Entity;
import com.nonsoft.ioc.IContainer;
import com.nonsoft.persistence.IDAO;

/**
 * 
 * <p>
 * Java5 version of Active Record
 * </p>
 * 
 * <p>Copyright: Copyright (c) 2003-2006 NonSoft.com</p>
 * 
 * @author Shawn Qian
 * @version 2.0, $Id$
 * @since
 */
public abstract class DiscussDomainObject extends AbstractDomainObject {

    private IContainer container;
    
    private IDAO dao;

    public DiscussDomainObject(Entity entity) {
        super(entity);
    }

    public IDAO getDAO() {
        return dao;
    }

    @InjectComponent()
    public void setDAO(IDAO daoSupport) {
        this.dao = daoSupport;
    }

    public IContainer getContainer() {
        return container;
    }

    @InjectComponent()
    public void setContainer(IContainer container) {
        this.container = container;
    }

    public void save() {
        Entity e = getEntity();
        dao.saveEntity(e);
    }

}