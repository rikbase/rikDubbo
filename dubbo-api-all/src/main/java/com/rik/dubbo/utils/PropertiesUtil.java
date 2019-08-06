package com.rik.dubbo.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PropertiesUtil {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map read(String propertiesFile) {
		ResourceBundle rb = ResourceBundle.getBundle(propertiesFile);
		Map map = new HashMap();
		Enumeration enu = rb.getKeys();
		while (enu.hasMoreElements()) {
			Object obj = enu.nextElement();
			Object objv = rb.getObject(obj.toString());
			map.put(obj, objv);
		}
		return map;
	}
}
