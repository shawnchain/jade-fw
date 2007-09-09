
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.Inject;
import com.nonsoft.annotation.Parameter;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.domain.IForum;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.view.Page;

@Transactional
public class EditForum extends Page {
    
    @Inject
    ForumService forumService;
    
    @Parameter(expr="request.param.id")
    Long forumId;
    
    @Override
    public void render() throws Throwable {
        if(forumId == null){
            throw new NullPointerException("Forum id is missing or invalid!");
        }
        try{
            IForum forum = forumService.loadForum(forumId);
            getContext().put("forum", forum);
        }catch(Exception e){
            throw new IllegalArgumentException("Forum not found with id " + forumId + ", possible cause: " + e.getMessage());
        }
    }

}
