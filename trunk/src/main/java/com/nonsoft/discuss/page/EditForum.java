
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.InjectParameter;
import com.nonsoft.discuss.domain.IForum;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.view.Page;

public class EditForum extends Page {
    
    @InjectComponent()
    ForumService forumService;
    
    @InjectParameter(expression="request.param.id")
    Long forumId;
    
    @Override
    public void render() throws Throwable {
        if(forumId == null){
            throw new NullPointerException("Forum id is null!");
        }
        try{
            IForum forum = forumService.loadForum(forumId);
            getContext().put("forum", forum);
        }catch(Exception e){
            throw new IllegalArgumentException("Forum not foudn with id " + forumId + ", possible cause: " + e.getMessage());
        }
    }

}
