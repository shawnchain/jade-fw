package com.nonsoft.discuss.service;

import org.picocontainer.Startable;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.entity.ForumEntity;
import com.nonsoft.discuss.entity.MessageEntity;
import com.nonsoft.discuss.entity.TopicEntity;
import com.nonsoft.persistence.hibernate3.HibernateDAOSupport;

public class InitDataService implements Startable {
    // FIXME remove me in production mode
    static {
        System.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    }

    @InjectComponent()
    private HibernateDAOSupport daoSupport;

    @Transactional()
    public void start() {
        try {
            System.out.println(">>>> Initializing Data Begin <<<<");
            ForumEntity forum = new ForumEntity();
            forum.setTitle("Forum -1");
            daoSupport.save(forum);

            TopicEntity topic = new TopicEntity();
            topic.setTitle("My first post!");
            topic.setForum(forum);
            daoSupport.save(topic);

            MessageEntity msg = new MessageEntity();
            msg.setTitle("message - 1");
            msg.setBody("First message");
            msg.setTopic(topic);
            daoSupport.save(msg);

        } catch (Exception e) {
            System.out.println("Error while initializing Data: " + e.getMessage());
        }
        System.out.println(">>>> Initializing Data End <<<<");
    }

    public void stop() {

    }

}
