package com.rik.dubbo.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Description;

import com.rik.dubbo.vo.FormModelViewVo;

public interface FormModelService extends Serializable{
	@Description("���ݱ�����ѯ����Ϣ")
	public List<FormModelViewVo> getList(FormModelViewVo formModelViewVo);
	@Description("��ѯ��DDL,Table|Create Table")
	public Map<String, Object> getFormDDL(String formName);
	@Description("��ʼ����Ӧ�����")
	public boolean initForm(String formName);
}
