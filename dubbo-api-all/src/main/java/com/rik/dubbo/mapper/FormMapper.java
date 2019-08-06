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
	
	@Description("��ȡ��ṹ��Ϣ")
	public List<FormModel> list(@Param("formModelViewVo") FormModelViewVo formModelViewVo);
	
	@Description("��ȡ������Ϣ")
	public Map<String, Object> showDDL(@Param("formName") String formName);
	
	@Description("init������Ϣ��drop,������Ϣ��")
	public void init(@Param("formModelViewVo") FormModelViewVo formModelViewVo);
		
}
