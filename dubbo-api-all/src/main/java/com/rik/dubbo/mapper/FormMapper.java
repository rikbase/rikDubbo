package com.rik.dubbo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Description;

import com.rik.dubbo.model.FormModel;
import com.rik.dubbo.vo.FormModelViewVo;

@Mapper
public interface FormMapper {
	
	@Description("获取表结构信息")
	public List<FormModel> list(@Param("formModelViewVo") FormModelViewVo formModelViewVo);
	
	@Description("获取建表信息")
	public Map<String, Object> showDDL(@Param("formName") String formName);
	
	@Description("init命令信息（drop,建表信息）")
	public void init(@Param("formModelViewVo") FormModelViewVo formModelViewVo);
		
}
