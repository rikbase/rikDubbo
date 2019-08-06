package com.rik.dubbo.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.rik.dubbo.constant.Constants;

public class JsonResult implements Serializable {
	
	private static final long serialVersionUID = -6609709005154902591L;
	
	Map<String, Object> resultMap = new HashMap<String, Object>();
	public JsonResult(String status,String msg){
		resultMap.clear();
		resultMap.put(Constants.STATUS.get(), StringUtils.isEmpty(status)?"0":status);
		resultMap.put(Constants.MSG.get(), StringUtils.isEmpty(msg)?"0":msg);
		resultMap.put(Constants.RST.get(), 0);
	}
	public JsonResult(String status,Object obj){
		resultMap.clear();
		resultMap.put(Constants.STATUS.get(), StringUtils.isEmpty(status)?"0":status);
		resultMap.put(Constants.MSG.get(), "0");
		resultMap.put(Constants.RST.get(), null==obj?0:obj);
	}
	
	public JsonResult(String status,String msg, Object obj){
		resultMap.clear();
		resultMap.put(Constants.STATUS.get(), StringUtils.isEmpty(status)?"0":status);
		resultMap.put(Constants.MSG.get(), StringUtils.isEmpty(msg)?"0":msg);
		resultMap.put(Constants.RST.get(), null==obj?0:obj);
	}
	
	public Object getKey(String key){
		return resultMap.get(key);
	}
	
	public String toString(){
		return JSON.toJSONString(resultMap);
	}
}
