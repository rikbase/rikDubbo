package com.rik.dubbo.conf;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Description;

import com.rik.dubbo.exceptions.BaseException;

@Description("配置信息自定义控制类")
public class CommonPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		try {
			/*Map<String,Object> map = PropertiesUtil.read("jdbc");
			props.setProperty("属性值", map.get("属性键").toString());*/
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException("configPropertis", "处理配置属性值异常", e.getMessage());
		}
	}
}
