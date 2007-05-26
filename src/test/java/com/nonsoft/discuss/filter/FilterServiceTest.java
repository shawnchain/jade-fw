
package com.nonsoft.discuss.filter;

import com.nonsoft.discuss.filter.FilterService;
import com.nonsoft.discuss.filter.IContentFilter;

import test.WebTestCase;

public class FilterServiceTest extends WebTestCase {
    public void testRender() {
        FilterService renderService = (FilterService)container.getComponentInstance(FilterService.class);
        assertNotNull(renderService);
        assertEquals("Foo", renderService.render("Foo"));
    }
    
    public void testRegisterRenderEngine() {
        FilterService renderService = (FilterService)container.getComponentInstance(FilterService.class);
        int idx = renderService.registerRenderEngine(new IContentFilter(){
            public String apply(String input) {
                return input.toLowerCase();
            }
        });
        assertEquals("foo", renderService.render("FOO"));
        renderService.unregisterRenderEngine(idx);
        assertEquals("FOO", renderService.render("FOO"));
    }

}
