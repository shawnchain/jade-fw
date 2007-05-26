package com.nonsoft.discuss.page;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.InjectParameter;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.domain.IMessage;
import com.nonsoft.discuss.domain.ITopic;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.action.ActionTarget;
import com.nonsoft.web.controller.RuntimeData;
import com.nonsoft.web.form.Field;
import com.nonsoft.web.form.Form;
import com.nonsoft.web.view.Page;

public class Reply extends Page {
    @InjectComponent()
    private ForumService forumService;
    
    @InjectParameter(expression = "request.param.id")
    private Long parentMessageId;
    
    @InjectParameter(expression = "request.param.tid")
    private Long topicId;

    @Override
    public void render() throws Throwable {
        if (topicId == null) {
            throw new IllegalArgumentException("No topic specified");
        }
        ITopic topic = forumService.loadTopic(topicId);
        //getContext().put("topic", topic);
        if(parentMessageId != null){
            IMessage msg = forumService.loadMessage(parentMessageId);
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
        form.addField(new Field("title","Re: " + topic.getTitle()));
        // Just need to store in the context
        getContext().put("form", form);
    }

    @Override
    @Transactional()
    public ActionTarget execute(RuntimeData rd) throws Throwable {
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
