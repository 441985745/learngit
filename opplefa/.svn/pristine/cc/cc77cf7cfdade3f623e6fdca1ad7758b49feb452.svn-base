package com.opple.fa;

import javax.servlet.ServletContextEvent;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.opple.fa.util.SysconfigInitListener;
import com.opple.fa.util.SystemBootstrap;
import com.opple.test.dbunit.BaseHopTestCase;

//@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {
		
		"classpath*:/spring-biz/spring-common.xml",
		"classpath*:/spring-biz/spring-datasource.xml",
		"classpath*:/spring-biz/spring-transaction.xml",
		"classpath*:/spring-biz/demo/spring-*.xml",
		"classpath*:/spring-biz/sap/spring-*.xml",
		"classpath*:/spring-biz/config/spring-factory.xml",
		"classpath*:/spring-biz/config/spring-work-address.xml",
		"classpath*:/spring-biz/config/spring-asset-unit.xml",
		"classpath*:/spring-biz/config/spring-budget-*.xml",
		
		})

@Ignore
@Transactional
public class BaseTestCase extends BaseHopTestCase {
	@BeforeClass
	public static void initEnv() throws Exception {
		SystemBootstrap bootstrap = new SystemBootstrap();
		bootstrap.afterPropertiesSet();
		SysconfigInitListener sysconfigInitListener = new SysconfigInitListener();
		ServletContextEvent contextEvent = new ServletContextEvent(new MockServletContext());
		sysconfigInitListener.onStartup(contextEvent);
	}
}
