package com.opple.fa;

import com.opple.fa.purchase.service.impl.DemandOrderServiceImplTest_SearchDemandOrder;
import com.opple.test.dbunit.DbunitManager;

import org.dbunit.DatabaseUnitException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 工具类，快速生成xml测试准备数据模板
 * @author WangXuzheng
 *
 */
public class ExportDb2Xml {
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, DatabaseUnitException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-biz/spring_exportdb.xml");
		DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
		DbunitManager dbunitManager = new DbunitManager(dataSource);
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put("TB_FA_DEMAND_ORDER", "select * from TB_FA_DEMAND_ORDER where 1=1");
		dbunitManager.exportDB2Xml(sqlMap, DemandOrderServiceImplTest_SearchDemandOrder.class);
	}
}
