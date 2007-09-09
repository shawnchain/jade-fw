
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.Inject;
import com.nonsoft.annotation.Parameter;
import com.nonsoft.annotation.SecurityCheck;
import com.nonsoft.annotation.ValidateForm;
import com.nonsoft.discuss.domain.IForum;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.action.ActionTarget;
import com.nonsoft.web.controller.RuntimeData;
import com.nonsoft.web.form.Form;
import com.nonsoft.web.view.Page;

public class Post extends Page {
    @Inject()
    private ForumService forumService;
    
    @Parameter(expr="request.param.forumId")
    private Long forumId;
    
    /* (non-Javadoc)
     * @see com.nonsoft.web.action.IAction#execute(com.nonsoft.web.controller.RuntimeData)
     */
    @ValidateForm    
    @SecurityCheck
    public ActionTarget execute(RuntimeData rd) throws Throwable {
        // !!! We do not need this code any more, because
        // The validateFormInterceptor will check whether form is valid or not
//        Form form = rd.getForm();
//        if(form == null || !form.isValid()){
//            // Nothing to save
//            //return ActionTarget.forward(rd,"/newPost.htm");
//            return null;
//        }

        if(forumId == null){
            throw new IllegalArgumentException("No forum id specified");
        }
        
        IForum forum = forumService.loadForum(forumId);
        
        Form form = rd.getForm();
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
