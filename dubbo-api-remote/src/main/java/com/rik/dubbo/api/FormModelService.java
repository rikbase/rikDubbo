package com.rik.dubbo.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Description;

import com.rik.dubbo.vo.FormModelViewVo;

public interface FormModelService extends Serializable{
	@Description("根据表名查询表信息")
	public List<FormModelViewVo> getList(FormModelViewVo formModelViewVo);
	@Description("查询表DDL,Table|Create Table")
	public Map<String, Object> getFormDDL(String formName);
	@Description("初始化对应表操作")
	public boolean initForm(String formName);
}
