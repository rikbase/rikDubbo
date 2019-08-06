package com.rik.dubbo.api;

import java.io.Serializable;

import org.springframework.context.annotation.Description;

import com.rik.dubbo.result.JsonResult;

public interface DataSrcService extends Serializable{
	@Description("��ѯ��������Ϣ")
	public JsonResult getInfo(String collectionName);
	
	@Description("ˢ������Դ")
	public JsonResult init(String collectionName);
}
