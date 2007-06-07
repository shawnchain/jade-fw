package com.nonsoft.discuss.service;

import org.apache.log4j.Logger;
import org.picocontainer.Startable;

import com.nonsoft.access.AuthException;
import com.nonsoft.access.AuthenticationService;
import com.nonsoft.annotation.InjectComponent;
import com.nonsoft.annotation.LazyLoad;
import com.nonsoft.web.context.Session;
import com.nonsoft.web.controller.RuntimeData;
import com.nonsoft.web.utils.RuntimeHelperProvider;

public class AuthService implements Startable {
    private static final Logger logger = Logger.getLogger(AuthService.class);

    @InjectComponent
    private RuntimeHelperProvider runtimeHelperProvider;
    
    @InjectComponent
    private AuthenticationService backendAuthServer;
    
    //@InjectComponent
    //private UserService userService;

    //---- Startable Interface ----
    public void start() {
        // register the AuthHelper
        runtimeHelperProvider.registerHelper(AUTH_HELPER, this);
    }

    public void stop() {
        runtimeHelperProvider.unregisterHelper(AUTH_HELPER);
    }
    
    //---- Auth Helper methods ----
    public boolean isAuthenticated() {
        return getAuthToken() != null;
    }

    // Special! Inject runtime instance
    @InjectComponent(key="runtime")
    @LazyLoad(cacheInstance=false)
    RuntimeData runtimeData;
    
    public Object getAuthToken() {
        Session session = getSession();
        if(session != null){
            return session.getValue(AUTH_TOKEN);
        }
        return null;
    }
    
    /**
     * Do authenticate against email & password
     * @param username
     * @param password
     * @return Object the auth token
     * @throws AuthException
     */
    public Object doAuth(String username, String password) throws AuthException{
        // Delegate to the backend auth service
        backendAuthServer.authenticate(username, password);
        
        // If runs here, auth success. Store the token in session
        // Here we'll use the email as the auth token
        Session session = getSession();
        if(session != null){
            session.setValue(AUTH_TOKEN, username);
        }else {
            logger.warn("Token is not saved, session is NULL!");
        }
        return username;
    }
    
    public void doLogout() throws AuthException{
        if(isAuthenticated()){
            getSession().setValue(AUTH_TOKEN, null);
        }else{
            throw new AuthException("User is not authenticated in yet");   
        }
    }
    
    /**
     * 
     * @param username
     * @param password
     */
    public void doRegister(String username, String password){
        //TODO
    }
    
    private Session getSession(){
        if(runtimeData != null){
            return runtimeData.getSession();
        }
        return null;
    }

    public static final String AUTH_TOKEN = "authToken";
    public static final String AUTH_HELPER = "authHelper";
}
