package com.opple.fa.webapp.listener;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.opple.exception.SysException;
import com.opple.session.listener.MaxSessionUtil;
import com.opple.util.HOPConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.opple.util.Env;

/**
 * 加载env.properties中的配置项，将静态资源地址和动态资源地址放到application变量中
 * @author WangXuzheng
 *
 */
public class SystemBootstrapListener implements ServletContextListener {
	/**
	 * CONFIG_FILE_PATH 系统变量配置文件路径
	 */
	private static final String CONFIG_FILE_PATH = "/env.properties";
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemBootstrapListener.class);
	public void contextInitialized(ServletContextEvent contextEvent) {
		//Properties properties = new Properties();
		Resource resource = new ClassPathResource(CONFIG_FILE_PATH);
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);

		Env.init(properties);
		HOPConstant.setAppName(Env.getProperty(Env.KEY_SERVER_NAME));
		//设置一些全局参数
			MaxSessionUtil.setMaxSessionKey(Env.getProperty(Env.KEY_SERVER_NAME) + "_MAX_SESSION_KEYS");

		ServletContext servletContext = contextEvent.getServletContext();
		servletContext.setAttribute("staticURL", Env.getProperty(Env.KEY_STATIC_URL));
		servletContext.setAttribute("dynamicURL", Env.getProperty(Env.KEY_DYNAMIC_URL));

		LOGGER.info(properties.toString());
		} catch (IOException e) {
			String errorMessage = "无法正常获取env.properties文件的位置，系统启动失败。";
			LOGGER.error(errorMessage);
			throw new SysException(errorMessage);
		}
	}
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
