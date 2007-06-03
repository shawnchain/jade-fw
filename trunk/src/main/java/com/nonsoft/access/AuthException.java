
package com.nonsoft.access;

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
public class AuthException extends Exception{
    private static final long serialVersionUID = -206512961137454244L;
    private String failReason;
    public String getFailReason() {
        return failReason;
    }

    public AuthException(String reason, Throwable cause) {
        super(reason, cause);
        this.failReason = reason;
    }

    public AuthException(String reason) {
        super(reason);
        this.failReason = reason;
    }
}