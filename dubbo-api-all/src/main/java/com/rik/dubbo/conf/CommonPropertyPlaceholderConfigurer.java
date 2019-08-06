package com.rik.dubbo.conf;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Description;

import com.rik.dubbo.exceptions.BaseException;

@Description("������Ϣ�Զ��������")
public class CommonPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		try {
			/*Map<String,Object> map = PropertiesUtil.read("jdbc");
			props.setProperty("����ֵ", map.get("���Լ�").toString());*/
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException("configPropertis", "������������ֵ�쳣", e.getMessage());
		}
	}
}
