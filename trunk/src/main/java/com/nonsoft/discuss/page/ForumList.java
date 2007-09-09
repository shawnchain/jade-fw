
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.Inject;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.view.Page;

@Transactional()
public class ForumList extends Page {
    @Inject()
    private ForumService forumService;
    
    public void render() throws Throwable {
//        ArrayList<Object> forums = new ArrayList<Object>();
//        for(Iterator i = forumService.listForums();i.hasNext();){
//            forums.add(i.next());
//        }
        //TODO eager load all forums ???
        getContext().put("forums", forumService.listForums());
    }

}
