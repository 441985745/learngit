package com.opple.fa.util;

import com.opple.util.Env;
import com.opple.util.SystemStartupListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Created by phoenix/lk on 21/11/2016.
 */
public class SysconfigInitListener implements SystemStartupListener {
    @Override
    public void onStartup(ServletContextEvent contextEvent) {
        ServletContext servletContext = contextEvent.getServletContext();
        servletContext.setAttribute("staticURL", Env.getProperty(Env.KEY_STATIC_URL));
        servletContext.setAttribute("dynamicURL", Env.getProperty(Env.KEY_DYNAMIC_URL));
    }
}
