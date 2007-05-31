//==============================================================================
// Created on 2007-5-30
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

package com.nonsoft.discuss.filter.radeox;

import org.radeox.filter.CacheFilter;
import org.radeox.filter.regex.LocaleRegexReplaceFilter;

/**
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

public class UnderLineFilter extends LocaleRegexReplaceFilter implements CacheFilter {
    protected String getLocaleKey() {
        return "filter.underline";
    }
}
