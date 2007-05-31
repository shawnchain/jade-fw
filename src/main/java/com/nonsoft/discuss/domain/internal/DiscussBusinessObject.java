package com.nonsoft.discuss.domain.internal;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.bo.BusinessObject;
import com.nonsoft.bo.Entity;
import com.nonsoft.ioc.IContainer;
import com.nonsoft.persistence.IDAO;

public abstract class DiscussBusinessObject extends BusinessObject {

    private IContainer container;
    private IDAO daoSupport;

    public DiscussBusinessObject(Entity entity) {
        super(entity);
    }

    public IDAO getDaoSupport() {
        return daoSupport;
    }

    @InjectComponent()
    public void setDaoSupport(IDAO daoSupport) {
        this.daoSupport = daoSupport;
    }

    public IContainer getContainer() {
        return container;
    }

    /**
     * 
     * @param container
     */
    @InjectComponent()
    public void setContainer(IContainer container) {
        this.container = container;
    }

    public Entity save() {
        Entity e = getEntity();
        daoSupport.saveEntity(e);
        return e;
    }

}