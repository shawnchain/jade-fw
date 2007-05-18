
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.InjectParameter;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.domain.IForum;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.action.ActionTarget;
import com.nonsoft.web.controller.RuntimeData;
import com.nonsoft.web.form.Form;
import com.nonsoft.web.view.Page;

public class Post extends Page {
    @InjectComponent()
    private ForumService forumService;
    
    @InjectParameter(expression="request.param.forumId")
    private Long forumId;
    
    /* (non-Javadoc)
     * @see com.nonsoft.web.action.IAction#execute(com.nonsoft.web.controller.RuntimeData)
     */
    @Transactional()
    public ActionTarget execute(RuntimeData rd) throws Throwable {
        Form form = rd.getForm();
        if(form == null || !form.isValid()){
            // Nothing to save
            //return ActionTarget.forward(rd,"/newPost.htm");
            return null;
        }
        
        if(forumId == null){
            throw new IllegalArgumentException("No forum id specified");
        }
        
        IForum forum = forumService.loadForum(forumId);
        
        String title = (String)form.getField("title").getValue();
        String body = (String)form.getField("body").getValue();
        forum.newTopic(title, body);
        // Saved successfully, redirect to the forum page
        return ActionTarget.redirect("forum.htm?id=" + forumId);
    }
    
    @Override
    public void render() throws Throwable {
        if(forumId == null){
            throw new IllegalArgumentException("No forum id specified");
        }
    }

}
