package com.nonsoft.discuss.domain.internal;

import java.util.Iterator;

import com.nonsoft.annotation.ConvertResult;
import com.nonsoft.bo.Entity;
import com.nonsoft.discuss.domain.IMessage;
import com.nonsoft.discuss.domain.ITopic;
import com.nonsoft.discuss.entity.MessageEntity;
import com.nonsoft.persistence.hibernate3.HibernateOperations;

public class Message extends Content implements IMessage {
    private static final long serialVersionUID = 8883638777458549694L;

    public Message(Entity entity) {
        super(entity);
    }

    public IMessage getParent() {
        return new Message(((MessageEntity) getEntity()).getParentMessage());
    }

    @ConvertResult(from = com.nonsoft.bo.Entity.class, to = com.nonsoft.discuss.domain.IMessage.class)
    public Iterator listChildrenMessages() {
        return (Iterator) getDaoSupport().execute(
                HibernateOperations.iterate("from MessageEntity m where m.parentMessage.id=" + getId()));
    }

    public ITopic getTopic() {
        return (ITopic) newDomainObject(ITopic.class, ((MessageEntity) getEntity()).getTopic());
    }

}
