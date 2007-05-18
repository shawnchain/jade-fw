
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.InjectParameter;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.domain.IForum;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.view.Page;

public class Forum extends Page {

    
    @InjectComponent()
    private ForumService forumService;
    
    @InjectParameter(expression="request.param.id")
    private Long forumId;
    
    @Override
    @Transactional()
    public void render() throws Throwable {
        if(forumId == null){
            throw new NullPointerException("No forum id specified!");
        }
        //We now support use lazy-loaded collections when rendering the template
        IForum forum =  forumService.loadForum(forumId);
        getContext().put("forum", forum);
    }

}