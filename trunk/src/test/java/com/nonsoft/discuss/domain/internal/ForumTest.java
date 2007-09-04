package com.nonsoft.discuss.domain.internal;

import java.util.Date;

import test.WebTestCase;

import com.nonsoft.discuss.entity.ForumEntity;
import com.nonsoft.persistence.IDAO;

public class ForumTest extends WebTestCase {
    public void testForum() throws Exception {
        ForumEntity forum = new ForumEntity();
        forum.setTitle("test-forum-1");
        forum.setCreationDate(new Date());
        
        // may broken with multipal DAO implementations
        // See jade_dao_hibernate3_components.xml
        IDAO dao = (IDAO) container.getComponentInstanceOfType(IDAO.class);
        assertNotNull(dao);
        dao.saveEntity(forum);
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
