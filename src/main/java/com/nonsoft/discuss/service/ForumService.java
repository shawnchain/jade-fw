package com.nonsoft.discuss.service;

import java.util.Iterator;

import com.nonsoft.annotation.ConvertResult;
import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.discuss.domain.IForum;
import com.nonsoft.discuss.domain.IMessage;
import com.nonsoft.discuss.domain.ITopic;
import com.nonsoft.discuss.entity.ForumEntity;
import com.nonsoft.discuss.entity.MessageEntity;
import com.nonsoft.discuss.entity.TopicEntity;
import com.nonsoft.domain.Entity;
import com.nonsoft.ioc.IContainer;
import com.nonsoft.persistence.IDAO;
import com.nonsoft.persistence.hibernate3.HibernateOperations;

public class ForumService {
    @InjectComponent()
    private IContainer container;

    @InjectComponent()
    private IDAO daoSupport;
    //private HibernateDAOSupport daoSupport;

    /**
     * The ListEntityInterceptor will convert the entity iterator to BO iterator
     * 
     * @ConvertResult(from=com.nonsoft.domain.Entity.class, to=com.nonsoft.discuss.domain.IForum.class)
     * @return
     */
    @ConvertResult(from = com.nonsoft.domain.Entity.class, to = com.nonsoft.discuss.domain.IForum.class)
    public Iterator listForums() {
        return (Iterator)daoSupport.execute(HibernateOperations.iterate("from ForumEntity"));
        //return daoSupport.iterate("from ForumEntity");
    }

    /**
     * @ConvertResult(from=com.nonsoft.bo.Entity.class, to=com.nonsoft.discuss.domain.IForum.class)
     * @param id
     * @return
     */
    public IForum loadForum(Long id) {
        // Entity entity = (Entity)daoSupport.loadEntity(ForumEntity.class, id);
        // return entity;
        Entity entity = (Entity) daoSupport.loadEntity(ForumEntity.class, id);
        if (entity == null) {
            return null;
        }
        return (IForum) container.getComponentInstance(IForum.class, new Class[] { Entity.class },
                new Object[] { entity });
    }
    
    public ITopic loadTopic(Long id){
        Entity entity = (Entity) daoSupport.loadEntity(TopicEntity.class, id);
        if (entity == null) {
            return null;
        }
        return (ITopic) container.getComponentInstance(ITopic.class, new Class[] { Entity.class },
                new Object[] { entity });
    }

    public IMessage loadMessage(Long id) {
        Entity entity = (Entity) daoSupport.loadEntity(MessageEntity.class, id);
        if (entity == null) {
            return null;
        }
        return (IMessage) container.getComponentInstance(IMessage.class, new Class[] { Entity.class },
                new Object[] { entity });
    }
}
