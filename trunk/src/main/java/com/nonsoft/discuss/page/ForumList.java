
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.view.Page;

public class ForumList extends Page {
    @InjectComponent()
    private ForumService forumService;
    
    @Transactional()
    public void render() throws Throwable {
//        ArrayList<Object> forums = new ArrayList<Object>();
//        for(Iterator i = forumService.listForums();i.hasNext();){
//            forums.add(i.next());
//        }
        getContext().put("forums", forumService.listForums());
    }

}
