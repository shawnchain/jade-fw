
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.InjectParameter;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.view.Page;

public class Forum extends Page {

    @InjectComponent()
    private ForumService forumService;
    
    @InjectParameter(expression="request.param.id")
    private Long forumId;
    
    @Override
    public void execute() throws Throwable {
        if(forumId == null){
            throw new NullPointerException("No forum id specified!");
        }
        getContext().put("forum", forumService.getForum(forumId));
    }

}
