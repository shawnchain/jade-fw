
package com.nonsoft.discuss.service;

import java.util.Iterator;

import com.nonsoft.annotation.ConvertResult;
import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.persistence.hibernate3.HibernateDAOSupport;

public class ForumService {
    @InjectComponent()
    private HibernateDAOSupport daoSupport;
    
    /**
     * The ListEntityInterceptor will convert the entity iterator to BO iterator
     * @ConvertResult(from=com.nonsoft.bo.Entity.class, to=com.nonsoft.discuss.domain.IForum.class)
     * @return
     */
    @ConvertResult(from=com.nonsoft.bo.Entity.class, to=com.nonsoft.discuss.domain.IForum.class)
    public Iterator listForums(){
        return daoSupport.iterate("from ForumEntity");
    }
}
