
package com.nonsoft.discuss.domain;

import java.util.Iterator;

public interface ITopic extends IContent{
    public IForum getForum();
    public Iterator listMessages();
}
