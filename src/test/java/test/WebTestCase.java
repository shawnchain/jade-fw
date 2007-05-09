
package test;

import java.util.HashMap;

import junit.framework.TestCase;

import com.nonsoft.ioc.ContainerRegistry;
import com.nonsoft.ioc.IContainer;
import com.nonsoft.ioc.IMutableContainer;
import com.nonsoft.ioc.config.Configuration;
import com.nonsoft.web.WebApplication;

public class WebTestCase extends TestCase {
    protected static IContainer container;
    protected void setUp() throws Exception {
        super.setUp();
        if(container == null){
            System.setProperty("hibernate.hbm2ddl.auto", "create-drop");
            
            ContainerRegistry reg = ContainerRegistry.getInstance();
            reg.init();
            Configuration rootConfig = Configuration.fromXMLRes(new String[]{"jade_dao_components.xml","jade_dao_hibernate3_components.xml"});
            rootConfig.configure((IMutableContainer)reg.getRootContainer());    
            Configuration bizConfig = Configuration.fromXMLRes(new String[]{"components.xml"},rootConfig);
            bizConfig.configure((IMutableContainer)reg.getBizContainer());
            
            HashMap data = new HashMap();
            data.put("contextPath", System.getProperty("user.dir") + "/src/main/webapp");
            WebApplication.init(data);
            container = WebApplication.getInstance().getModuleFactory().getDefaultModule().getContainer();//reg.getBizContainer();
        }
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testNoop(){
        assertNotNull(container);
    }
    
}
