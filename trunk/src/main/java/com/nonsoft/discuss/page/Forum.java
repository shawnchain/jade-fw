
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.Inject;
import com.nonsoft.annotation.Parameter;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.domain.IForum;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.action.ActionTarget;
import com.nonsoft.web.controller.RuntimeData;
import com.nonsoft.web.view.Page;

@Transactional()
public class Forum extends Page {

    
    @Inject
    private ForumService forumService;
    
    /*Inject(expr="request.parameters.id",optional=true,fuzzyType=true)*/    
    @Parameter(expr="request.param.id") 
    private Long forumId;
    
    @Override
    public void render() throws Throwable {
        if(forumId == null){
            throw new NullPointerException("No forum id specified!");
        }
        //We now support use lazy-loaded collections when rendering the template
        IForum forum =  forumService.loadForum(forumId);
        getContext().put("forum", forum);
    }

    @Override
    public ActionTarget execute(RuntimeData runtimeData) throws Throwable {
        if(forumId == null){
            // No forum id specified, just forward to the "forum_list" view
            return ActionTarget.forward(runtimeData, "/forum_list.vm");
        }
        return null;
    }

}
