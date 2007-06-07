package com.nonsoft.discuss.entity;

import java.util.Set;

import com.nonsoft.domain.Entity;

public class MessageEntity extends Entity {
    String title;

    String body;

    String tag;

    java.util.Date creationDate;

    java.util.Date modificationDate;

    // Long authorId;
    String creator; // user id of the author

    Set childrenMessages; // Replied messages, reverse=true

    MessageEntity parentMessage; // parent message

    TopicEntity topic;

    // public Long getAuthorId() {
    // return authorId;
    // }
    // public void setAuthorId(Long authorId) {
    // this.authorId = authorId;
    // }
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set getChildrenMessages() {
        return childrenMessages;
    }

    public void setChildrenMessages(Set childrenMessages) {
        this.childrenMessages = childrenMessages;
    }

    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }

    public java.util.Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(java.util.Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public MessageEntity getParentMessage() {
        return parentMessage;
    }

    public void setParentMessage(MessageEntity parentMessage) {
        this.parentMessage = parentMessage;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

}
