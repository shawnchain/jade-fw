
package com.nonsoft.discuss.page;

import com.nonsoft.annotation.Inject;
import com.nonsoft.annotation.Parameter;
import com.nonsoft.annotation.Transactional;
import com.nonsoft.discuss.domain.ITopic;
import com.nonsoft.discuss.service.ForumService;
import com.nonsoft.web.view.Page;

public class Topic extends Page {
    @Parameter(expr="request.param.id")
    private Long topicId;
    
    @Inject()
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
