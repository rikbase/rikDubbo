package com.rik.dubbo.api;

import java.io.Serializable;

import org.springframework.context.annotation.Description;

import com.rik.dubbo.result.JsonResult;

public interface DataSrcService extends Serializable{
	@Description("查询表数据信息")
	public JsonResult getInfo(String collectionName);
	
	@Description("刷新数据源")
	public JsonResult init(String collectionName);
}
