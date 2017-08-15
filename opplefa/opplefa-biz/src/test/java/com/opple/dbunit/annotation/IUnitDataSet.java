package com.opple.dbunit.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标注一个测试类，用AbstractDatasetProviderListener来准备测试数据
 *
 * @author WangXuzheng
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface IUnitDataSet {
	/**
	 * 需要读取的测试准备文件
	 *
	 * @return
	 */
	String[] value() default {
		
	};

	/**
	 * 在spring配置文件中配置的数据源的bean id
	 * 
	 * @return
	 */
	String dataSource() default "dataSource";
}
