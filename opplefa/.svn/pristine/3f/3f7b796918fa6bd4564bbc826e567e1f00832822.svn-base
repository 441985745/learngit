package com.opple.fa.webapp.controller;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import com.opple.util.Pager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author WangXuzheng
 *
 */
public class BaseOpenApiController {
	protected JSONObject pagerToDataGridObject(Pager<?> resultPager) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", resultPager == null ? "0" : resultPager.getTotal());
		jsonMap.put("rows",  resultPager == null ? "" : resultPager.getRecords());
        return JSONObject.fromObject(jsonMap);
	}
	protected JSONObject pagerToDataGridJson(Pager<?> resultPager) {
		JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        if (resultPager != null && resultPager.getRecords() != null) {
       	    PropertyDescriptor[] props = null;
        	for (Object bean : resultPager.getRecords()) {
            	JSONObject jsonObj = new JSONObject();
            	try {
                    props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
                } catch (Exception e) {
                	e.getMessage();
                }
            	if (props != null) {
                    for (int i = 0; i < props.length; i++) {
                        try {
                            String name = props[i].getName();
                            String value = props[i].getReadMethod().invoke(bean).toString();
                            jsonObj.put(name, value);
                        } catch (Exception e) {
                        	e.getMessage();
                        }
                    }
                }
                jsonArray.add(jsonObj);
            }
        }
		json.put("total", resultPager == null ? "0" : resultPager.getTotal());
		json.put("rows", resultPager == null ? "" : jsonArray);
        return json;
	}
}
