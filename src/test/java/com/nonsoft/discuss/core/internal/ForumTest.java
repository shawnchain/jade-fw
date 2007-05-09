package com.nonsoft.discuss.core.internal;

import java.util.Date;

import test.WebTestCase;

import com.nonsoft.discuss.entity.ForumEntity;
import com.nonsoft.persistence.hibernate3.HibernateDAOSupport;

public class ForumTest extends WebTestCase {
    public void testForum() throws Exception {
        ForumEntity forum = new ForumEntity();
        forum.setTitle("test-forum-1");
        forum.setCreationDate(new Date());
        HibernateDAOSupport dao = (HibernateDAOSupport) container.getComponentInstance(HibernateDAOSupport.class);
        dao.save(forum);
//        ISessionManager sm = (ISessionManager) container.getComponentInstance(ISessionManager.class);
//        try {
//            sm.beginTransaction();
//            // HibernateSessionAdapter hs = (HibernateSessionAdapter)sm.currentSession();
//            
//            sm.commitTransaction();
//        } catch (Exception e) {
//            sm.rollbackTransaction();
//            e.printStackTrace();
//        } finally {
//            sm.endTransaction();
//        }
        assertNotNull(container);
        
    }
}
