package com.nonsoft.discuss.service;

import org.picocontainer.Startable;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.entity.ForumEntity;
import com.nonsoft.discuss.entity.TopicEntity;
import com.nonsoft.discuss.entity.UserEntity;
import com.nonsoft.ioc.IContainer;
import com.nonsoft.persistence.IDAO;

public class InitDataService implements Startable {
    // FIXME remove me in production mode
    static {
        System.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    }

    @InjectComponent()
    private IDAO daoSupport;

    @InjectComponent()
    IContainer container;

    @Transactional()
    public void start() {
        try {
            System.out.println(">>>> Initializing Data Begin <<<<");
            ForumEntity forum = new ForumEntity();
            forum.setTitle("Generic");
            forum.setCreator("System");
            forum.setCreationDate(new java.util.Date());
            daoSupport.saveEntity(forum);

            TopicEntity topic = new TopicEntity();
            topic.setCreator("System");
            topic.setCreationDate(new java.util.Date());
            topic.setTitle("Welcome !");
            topic.setBody(
                    "Welcome to the Discuss Forum, the demo application powered by JADE Framework!\n\n" +
                    "JADE is a full-stack framework for Java application development, and now hosted at " +
                    "http://code.google.com/p/jade-fw/\n\n" +
                    "Enjoy!\n\n" +
                    "~~ ''Shawn'' ~~");
            topic.setForum(forum);
            daoSupport.saveEntity(topic);

            UserEntity user = new UserEntity();
            user.setName("admin");
            user.setEmail("admin");
            user.setCreationDate(new java.util.Date());
            daoSupport.saveEntity(user);
            // MessageEntity msg = new MessageEntity();
            // msg.setTitle("My first post!");
            // msg.setBody("First message");
            // msg.setTopic(topic);
            // daoSupport.save(msg);
        } catch (Exception e) {
            System.out.println("Error while initializing Data: " + e.getMessage());
        }
        System.out.println(">>>> Initializing Data End <<<<");
    }

    public void stop() {

    }

}
