
package com.nonsoft.discuss.page;

import java.util.ArrayList;
import java.util.Iterator;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.view.Page;

public class ForumList extends Page {
    @InjectComponent()
    private ForumService forumService;
    
    @Transactional()
    public void execute() throws Throwable {
        //NOTE We also can pass the iterator into context directly, but 
        // need the OpenSessionInView pattern.
        ArrayList forums = new ArrayList();
        for(Iterator i = forumService.listForums();i.hasNext();){
            forums.add(i.next());
        }
        getContext().put("forums", forums);
    }

}
