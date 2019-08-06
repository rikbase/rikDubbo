package com.rik.dubbo.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rik.dubbo.api.IDubboRemoteService;
import com.rik.dubbo.data.DataManager;

@Service("iDubboRemoteService")
public class IDubboRemoteServiceImpl implements IDubboRemoteService{
	private static final long serialVersionUID = -4818890485653942523L;
	private Logger logger = Logger.getLogger(getClass());
	private static String SYS_SIGN = "IDRS";
	public void sayHello(String info) {
		// TODO Auto-generated method stub
		logger.trace(SYS_SIGN+": RPC TRACE!");
		logger.debug(SYS_SIGN+": RPC DEBUG!");
		logger.info(SYS_SIGN+": RPC INFO!");
		logger.warn(SYS_SIGN+": RPC WARN!");
		logger.error(SYS_SIGN+": RPC ERROR!");
		logger.fatal(SYS_SIGN+": RPC FATAL!");
	}
	public Object getdb() {
		new DataManager();
		// TODO Auto-generated method stub
		return DataManager.dataSource;
	}
}
