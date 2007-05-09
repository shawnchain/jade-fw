
package com.nonsoft.discuss.domain;

import java.util.Iterator;

public interface IMessage extends IContent{
    public IMessage getParent();
    public Iterator listChildrenMessages();
}
