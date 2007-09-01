package com.nonsoft.discuss.page;

import com.nonsoft.annotation.Inject;
import com.nonsoft.annotation.Parameter;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.domain.IMessage;
import com.nonsoft.discuss.domain.ITopic;
import com.nonsoft.discuss.entity.MessageEntity;
import com.nonsoft.discuss.entity.TopicEntity;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.action.ActionTarget;
import com.nonsoft.web.controller.RuntimeData;
import com.nonsoft.web.form.Field;
import com.nonsoft.web.form.Form;
import com.nonsoft.web.view.Page;

public class Reply extends Page {
    @Inject()
    private ForumService forumService;
    
    @Parameter(expr = "request.param.id")
    private Long parentMessageId;
    
    @Parameter(expr = "request.param.tid")
    private Long topicId;

    @Parameter(expr = "request.param.quote")
    private Boolean quote = false;
    
    @Parameter(expr = "request.param.event")
    private String event;

    @Override
    public void render() throws Throwable {
        if (topicId == null) {
            throw new IllegalArgumentException("No topic specified");
        }
        ITopic topic = forumService.loadTopic(topicId);
        IMessage msg = null;
        //getContext().put("topic", topic);
        if(parentMessageId != null){
            msg = forumService.loadMessage(parentMessageId);
            // sanity check
            if(topic.getId() != msg.getTopic().getId()){
                throw new IllegalArgumentException("The message you're replying is not blong to the topic " + topic.getTitle());
            }
            getContext().put("reply", msg);
        }else{
            getContext().put("reply", topic);   
        }
        
        // We need to setup the form title with default value, "Re: xxxx"
        Form form = new Form("postForm");
        if(msg != null){
            form.addField(new Field("title","Re: " + ((MessageEntity)msg.getEntity()).getTitle()));
            if(quote){
                form.addField(new Field("body","{quote}\n" + ((MessageEntity)msg.getEntity()).getBody() + "\n{quote}\n"));   
            }
        }else{
            form.addField(new Field("title","Re: " + ((TopicEntity)topic.getEntity()).getTitle()));
            if(quote){
                form.addField(new Field("body","{quote}\n" + ((TopicEntity)topic.getEntity()).getBody() + "\n{quote}\n"));   
            }
        }
        // Just need to store in the context
        getContext().put("form", form);
    }

    @Override
    @Transactional()
    public ActionTarget execute(RuntimeData rd) throws Throwable {
        // Checke event
        if("cancel".equals(event)){
            if(topicId != null){
                return ActionTarget.redirect("/topic.htm?id=" + topicId);
            }else{
                // No topic id specified, return to the forum list page
                return ActionTarget.redirect("/forum.htm");
            }
        }
        
        Form form = rd.getForm();
        if(form == null || !form.isValid()){
            // Nothing to save
            //return ActionTarget.forward(rd,"/newPost.htm");
            return null;
        }
        
        if (topicId == null) {
            throw new IllegalArgumentException("No topic specified");
        }
        ITopic topic = forumService.loadTopic(topicId);
        
        IMessage replyMessage = null;
        if(parentMessageId != null){
            replyMessage = forumService.loadMessage(parentMessageId);
        }
        String title = (String)form.getField("title").getValue();
        String body = (String)form.getField("body").getValue();
        
        topic.newReply(title, body,replyMessage);
        // Saved successfully, redirect to the forum page
        return ActionTarget.redirect("/topic.htm?id=" + topicId);
    }
    
    
}
