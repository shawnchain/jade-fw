//==============================================================================
// Created on 2007-5-27
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

import java.util.Locale;

import org.radeox.api.engine.RenderEngine;
import org.radeox.api.engine.context.InitialRenderContext;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.engine.BaseRenderEngine;
import org.radeox.engine.context.BaseInitialRenderContext;
import org.radeox.engine.context.BaseRenderContext;

/**
 * <p>
 * Content filter that using radeox
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

public class RadeoxContentFilter implements IContentFilter {
    private RenderEngine engine;

    public RadeoxContentFilter() {
        init();
    }

    public void init() {
        InitialRenderContext initialContext = new BaseInitialRenderContext();
        initialContext.set(RenderContext.INPUT_LOCALE, new Locale("moin", ""));
        engine = new BaseRenderEngine(initialContext);
        //engine = new BaseRenderEngine();
    }

    public String apply(String input) {
        RenderContext context = new BaseRenderContext();
        return engine.render(input, context);
    }
}
