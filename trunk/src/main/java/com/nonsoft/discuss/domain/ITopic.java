
package com.nonsoft.discuss.domain;

import java.util.Iterator;

public interface ITopic extends IContent{
    /**
     * Get the blong forum
     * @return
     */
    public IForum getForum();
    
    /**
     * List all child messages
     * @return
     */
    public Iterator listMessages();
    
    /**
     * 
     * @param title
     * @param body
     * @return
     */
    public IMessage newReply(String title, String body);
    
    public IMessage newReply(String title, String body, IMessage replyingMessage);
    
    public Integer countMessages();
}
