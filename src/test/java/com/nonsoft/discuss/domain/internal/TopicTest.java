
package com.nonsoft.discuss.domain.internal;

import java.util.Iterator;

import test.WebTestCase;

import com.nonsoft.discuss.domain.IForum;
import com.nonsoft.discuss.domain.ITopic;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.persistence.ISessionManager;

public class TopicTest extends WebTestCase {
    private ForumService forumService;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        forumService = (ForumService)container.getComponentInstance(ForumService.class);
    }
    
    public void testCountTopicMessages() throws Exception{
        ISessionManager sm = (ISessionManager)container.getComponentInstance(ISessionManager.class);
        assertNotNull(sm);
        sm.beginTransaction();
        assertNotNull(forumService);
        IForum forum = forumService.loadForum(new Long(1));
        assertNotNull(forum);
        for (Iterator iter = forum.listTopics(); iter.hasNext();) {
            ITopic topic = (ITopic)iter.next();
            assertEquals(new Integer(0),topic.countMessages());
        }
        sm.endTransaction();
    }
}
