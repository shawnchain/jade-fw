
package com.nonsoft.discuss.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 
 * <p>
 * </p>
 * 
 * <p>Copyright: Copyright (c) 2003-2006 NonSoft.com</p>
 * 
 * @author Shawn Qian
 * @version 2.0, $Id$
 * @since
 */
public class FilterService {
    private List<IContentFilter> filters = new ArrayList<IContentFilter>();
    public int registerRenderEngine(IContentFilter engine){
          if(filters.add(engine)){
              return filters.size() -1;
          }else{
              return -1;
          }
    }
    
    public void unregisterRenderEngine(int index){
        if(index >=0 && index < filters.size()){
            filters.remove(index);   
        }
    }
    
    public String render(String input){
        if(input == null){
            return null;
        }
        String output = input;
        for(Iterator<IContentFilter> i = filters.iterator();i.hasNext();){
            output = i.next().apply(output);
        }
        return output;
    }
}
