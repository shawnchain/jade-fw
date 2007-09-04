package com.nonsoft.discuss.filter;

import java.util.Date;

import test.WebTestCase;

import com.nonsoft.discuss.domain.IContent;
import com.nonsoft.domain.Entity;
import com.nonsoft.ioc.builder.defaults.AspectBuilder;
import com.nonsoft.ioc.builder.defaults.ComponentBuilder;
import com.nonsoft.ioc.internal.defaults.DefaultCoreContainer;

public class ContentInterceptorTest extends WebTestCase {

    public void testInvoke() {
        DefaultCoreContainer c = new DefaultCoreContainer(container.getParent());
        AspectBuilder ab = new AspectBuilder();
        ab.beginPointcut().byInterface(IContent.class).and().byMethodName("getTitle|getBody").bindInterceptor(
                "contentInterceptor").endPointcut();
        c.registerComponent(new ComponentBuilder(FooContent.class).setAspectBuilder(ab).getAdapter());
        FooContent content = (FooContent)c.getComponentInstance(FooContent.class);
        assertNotNull(content);
        assertEquals("FOO", content.getTitle());
        assertEquals("BAR, by __author__", content.getBody());
        content.setBody("Changed");
        assertEquals("BAR, by __author__", content.getBody());
        content.modificationDate = new java.util.Date();
        assertEquals("Changed", content.getBody());
        //System.out.println(content.getBody());
    }

    public static class FooContent implements IContent {
        public void setBody(String body){
            this.body = body;
        }
        private String title = "FOO";

        private String body = "BAR, by __author__";

        private Date creationDate = new java.util.Date();

        private Date modificationDate = new java.util.Date();

        // public FooContent(String title, String body){
        // this.title = title;
        // this.body = body;
        // }
        
        public String getBody() {
            return body;
        }

        public Date getCreationDate() {
            return creationDate;
        }

        public String getCreator() {
            // TODO Auto-generated method stub
            return null;
        }

        public Entity getEntity() {
            // TODO Auto-generated method stub
            return null;
        }

        public Long getId() {
            // TODO Auto-generated method stub
            return null;
        }

        public Date getModificationDate() {
            return modificationDate;
        }

        public String getTitle() {
            // TODO Auto-generated method stub
            return title;
        }

        public void save() {
            // TODO Auto-generated method stub
            return ;
        }

        public Object queryInterface(Class type) {
            // TODO Auto-generated method stub
            return null;
        }

        public void setTitle(String title) {
            // TODO Auto-generated method stub
            
        }

    }

}
