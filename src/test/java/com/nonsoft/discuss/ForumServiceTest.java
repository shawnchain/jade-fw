package com.nonsoft.discuss;

import java.util.Iterator;

import test.WebTestCase;

import com.nonsoft.discuss.domain.IForum;
import com.nonsoft.discuss.domain.IMessage;
import com.nonsoft.discuss.domain.ITopic;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.persistence.ISessionManager;

public class ForumServiceTest extends WebTestCase {

    public void testListForum() throws Exception {
        // DAOHelper dao = (DAOHelper) container.getComponentInstance(DAOHelper.class);
        // for (Iterator i = dao.list("from ForumEntity").iterator(); i != null && i.hasNext();) {
        // ForumEntity _e = (ForumEntity)i.next();
        // System.out.println(">>>" + _e.getId() + " - " + _e.getTitle());
        // }
        // org.apache.commons.beanutils.PropertyUtils.getpro
        ISessionManager sm = (ISessionManager) container.getComponentInstance(ISessionManager.class);
        try {
            sm.beginTransaction();
            ForumService fs = (ForumService) container.getComponentInstance(ForumService.class);
            for (Iterator i = fs.listForums(); i.hasNext();) {
                IForum forum = (IForum) i.next();
                System.out.println(forum.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sm.endTransaction();
        }
    }
    
    public void testDumpAll() throws Exception{
        ISessionManager sm = (ISessionManager) container.getComponentInstance(ISessionManager.class);
        try {
            sm.beginTransaction();
            ForumService fs = (ForumService) container.getComponentInstance(ForumService.class);
            for (Iterator i = fs.listForums(); i.hasNext();) {
                IForum forum = (IForum) i.next();
                System.out.println("Forum: " + forum.getTitle());
                for(Iterator j = forum.listTopics();j.hasNext();){
                    ITopic topic = (ITopic)j.next();
                    System.out.println("  Topic: " + topic.getTitle());
                    for(Iterator k = topic.listMessages();k.hasNext();){
                        IMessage msg = (IMessage)k.next();
                        System.out.println("    Message: " + msg.getTitle());
                        System.out.println("    --------------------------------------------------------------------------------");
                        System.out.println("    " + msg.getBody());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            sm.endTransaction();
        }
    }

}
