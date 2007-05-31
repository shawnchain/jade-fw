//==============================================================================
// Created on 2007-5-27
// $Id$
//==============================================================================
//  Copyright (C) <2006,2007>  Shawn Qian, shawn.chain@gmail.com
//
//  This library is free software; you can redistribute it and/or
//  modify it under the terms of the GNU Lesser General Public
//  License as published by the Free Software Foundation; either
//  version 2.1 of the License, or (at your option) any later version.
//
//  This library is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//  Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public
//  License along with this library; if not, write to the Free Software
//  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//==============================================================================

package com.nonsoft.discuss.page;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.InjectParameter;
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

/**
 * <p>
 * </p>
 * 
 * <p>Copyright: Copyright (c) 2003-2006 NonSoft.com</p>
 * 
 * @author Shawn Qian
 * @version 2.0, $Id$
 * @since
 */

public class Edit extends Page {
    @InjectComponent()
    private ForumService forumService;
    
    @InjectParameter(expression = "request.param.id")
    private Long messageId;
    
    @InjectParameter(expression = "request.param.tid")
    private Long topicId;
    
    @InjectParameter(expression = "request.param.event")
    private String event;
    
    @Override
    @Transactional()
    public void render() throws Throwable {
        if (topicId == null) {
            throw new IllegalArgumentException("No topic specified");
        }
        ITopic topic = forumService.loadTopic(topicId);
        IMessage msg = null;
        //getContext().put("topic", topic);
        if(messageId != null){
            msg = forumService.loadMessage(messageId);
            // sanity check
            if(topic.getId() != msg.getTopic().getId()){
                throw new IllegalArgumentException("The message you're editing is not blong to the topic " + topic.getTitle());
            }
            getContext().put("edit", msg);
        }else{
            getContext().put("edit", topic);   
        }
        
        // We need to setup the form title with default value, "Re: xxxx"
        Form form = new Form("postForm");
        
        //FIXME store the current author ?
        if(msg != null){
            form.addField(new Field("title",((MessageEntity)msg.getEntity()).getTitle()));
            form.addField(new Field("body",((MessageEntity)msg.getEntity()).getBody()));
        }else{
            form.addField(new Field("title",((TopicEntity)topic.getEntity()).getTitle()));
            form.addField(new Field("body",((TopicEntity)topic.getEntity()).getBody()));
        }
        // Just need to store in the context
        getContext().put("form", form);
    }

    @Override
    @Transactional()
    public ActionTarget execute(RuntimeData rd) throws Throwable {
        // Check event
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
            return null;
        }
        String title = (String)form.getField("title").getValue();
        String body = (String)form.getField("body").getValue();

        if (topicId == null) {
            throw new IllegalArgumentException("No topic specified");
        }
        ITopic topic = forumService.loadTopic(topicId);        
        IMessage message = null;
        
        //FIXME Should check whether current user is the author of the topic/message being edited.
        if(messageId != null){
            message = forumService.loadMessage(messageId);
            message.setTitle(title);
            message.setBody(body);
            message.save();
        }else{
            // No message id specified, means edit the topic
            topic.setTitle(title);
            topic.setBody(body);
            topic.save();
        }
        // Saved successfully, redirect to the forum page
        return ActionTarget.redirect("/topic.htm?id=" + topicId);
    }
    
    
}
