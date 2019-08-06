package com.rik.dubbo.exceptions;

import org.springframework.context.annotation.Description;

@Description("��װ�Զ����쳣��Ϣ��������ʾ")
public class BaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String exNo,exce0, exce1;
	
	public BaseException(String exNo,String exce0, String exce1){
		super(exce1);
		this.exce0 =exce0;
		this.exce1 = exce1;
		this.exNo = exNo;
	}
	public String getExNo() {
		return exNo;
	}
	public String getExce0() {
		return exce0;
	}
	public String getExce1() {
		return exce1;
	}

}
