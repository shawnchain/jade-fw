//==============================================================================
// Created on 2007-5-26
// $Id$
//==============================================================================
//  Copyright (C) <2006,2007>  Shawn Qian, shawn.chain@gmail.com
//
//  This library is free software; you can redistribute it and/or
//  modify it under the terms of the GNU Lesser General Public
//  License as published by the Free Software Foundation; either
//  version 2.1 of the License, or (at your option) any later version.
//
//  This library is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//  Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public
//  License along with this library; if not, write to the Free Software
//  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//==============================================================================

package com.nonsoft.discuss.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.nonsoft.annotation.AnnotationSupport;
import com.nonsoft.annotation.Inject;
import com.nonsoft.discuss.domain.IContent;

/**
 * 
 * <p>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2003-2006 NonSoft.com
 * </p>
 * 
 * @author Shawn Qian
 * @version 2.0, $Id$
 * @since
 */
public class ContentInterceptor implements MethodInterceptor {
    public ContentInterceptor(){
        System.out.println("!!! Content Interceptor created !!!");
    }
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ContentInterceptor.class);

    private Map<String, CacheEntry> cache = java.util.Collections.synchronizedMap(new HashMap<String, CacheEntry>());

    // @Inject()
    //private boolean cacheResult = true;

    @Inject()
    private FilterService renderService;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        IContent content = (IContent) invocation.getThis();
        ApplyFilter annotation = (ApplyFilter)AnnotationSupport.getAttributeRepository().getAttribute(invocation.getMethod(), ApplyFilter.class);
        
        if (annotation != null && !annotation.cache()) {
            // cache is disabled by annotation
            return renderService.render((String) invocation.proceed());
        }
        if(annotation == null){
            logger.warn("No ApplyFilter annotation found on method " + invocation.getMethod() + ", Please check the pointcut config!");
        }

        // Else try to cache the content
        String key = getCacheKey(content, invocation.getMethod().getName());
        System.out.println("!!! Cache key is: " + key);
        CacheEntry entry = cache.get(key);
        if (entry != null && entry.timestamp.equals(getTimestamp(content))) {
            return entry.content;
        } else {
            entry = new CacheEntry();
            //FIXME what if content is NULL ? still cache it ??
            entry.content = renderService.render((String) invocation.proceed());
            entry.timestamp = getTimestamp(content);
            if (entry.timestamp == null) {
                logger.warn("No timestamp found on target " + content + ", filtered content will not be cached");
                return entry.content;
            }
            cache.put(key, entry);
            return entry.content;
        }
    }

    private static Date getTimestamp(IContent content) {
        Date result = content.getModificationDate();
        if (result == null) {
            result = content.getCreationDate();
        }
        return result;
    }
    
    private static String getCacheKey(IContent content, String customized){
        return content.getClass().getName() + "#" +content.getId() + "@" + customized;
    }

    // FIXME use more robust cache
    private static class CacheEntry {
        private Date timestamp;

        private String content;
    }

}
