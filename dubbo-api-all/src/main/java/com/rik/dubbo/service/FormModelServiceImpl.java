package com.rik.dubbo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rik.dubbo.api.FormModelService;
import com.rik.dubbo.mapper.FormMapper;
import com.rik.dubbo.model.FormModel;
import com.rik.dubbo.vo.FormModelViewVo;

@Service("formModelService")
@Description("FISI")
public class FormModelServiceImpl implements FormModelService {
	private static final long serialVersionUID = -6251532060274774188L;
	private static String SYS_SIGN="FISI";
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private FormMapper formMapper;
	
	public List<FormModelViewVo> getList(FormModelViewVo formModelViewVo) {
		// TODO Auto-generated method stub
		try {
			return makeList(formMapper.list(formModelViewVo));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":getList-"+e.getMessage(), e.getCause());
			return null;
		}
	}

	public Map<String, Object> getFormDDL(String formName) {
		// TODO Auto-generated method stub
		try {
			return formMapper.showDDL(formName);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":getFormDDL-"+e.getMessage(), e.getCause());
			return null;
		}
	}
	
	@Description("propagation：传播七种模式，isolation:隔离四种模式")
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public boolean initForm(String formName) {
		// TODO Auto-generated method stub
		try {
			FormModelViewVo formModelViewVo = new FormModelViewVo();
			formModelViewVo.setFormName(formName);
			formMapper.init(formModelViewVo);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(SYS_SIGN+":initForm-"+e.getMessage(), e.getCause());
			return false;
		}
	}
	
	private List<FormModelViewVo> makeList(List<FormModel> models){
		if (models == null ||models.size() ==0) return null;
		FormModelViewVo modelViewVo= new FormModelViewVo();
		List<FormModelViewVo> list = new ArrayList<FormModelViewVo>();
		for (FormModel formModel : models) {
			modelViewVo = new FormModelViewVo();
			BeanUtils.copyProperties(formModel, modelViewVo);
			list.add(modelViewVo);
		}	
		return list;
	}
}
