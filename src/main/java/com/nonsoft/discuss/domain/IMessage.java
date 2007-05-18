package com.nonsoft.discuss.domain;

import java.util.Iterator;

public interface IMessage extends IContent {

    public Iterator listChildrenMessages();
    
    public ITopic getTopic();
    
    public IMessage getParent();
}
