
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.InjectParameter;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.domain.ITopic;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.view.Page;

public class Topic extends Page {
    @InjectParameter(expression="request.param.id")
    private Long topicId;
    
    @InjectComponent()
    private ForumService forumService;
    
    @Override
    @Transactional()
    public void render() throws Throwable {
        if(topicId == null){
            throw new IllegalArgumentException("No topic id specified!");
        }
        ITopic topic = forumService.loadTopic(topicId);
        getContext().put("topic", topic);
        getContext().put("forum",topic.getForum());
    }
}
