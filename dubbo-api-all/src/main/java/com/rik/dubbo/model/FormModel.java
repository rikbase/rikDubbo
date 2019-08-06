package com.rik.dubbo.model;

import java.io.Serializable;

public class FormModel implements Serializable {

	/**
	 * 创建业务表对象信息，以实现自动生成对应各级类与配置文件；
	 */
	private static final long serialVersionUID = 1L;
	
	private String formName;
	private String columnName;
	private String columnComment;
	private String dataType;
	private long characterMaximumLength;
	private String isNullable;
	private String datatypeShow;
	private boolean isNullableShow;
	
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public long getCharacterMaximumLength() {
		return characterMaximumLength;
	}
	public void setCharacterMaximumLength(long characterMaximumLength) {
		this.characterMaximumLength = characterMaximumLength;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getDatatypeShow() {
		return datatypeShow;
	}
	public void setDatatypeShow(String datatypeShow) {
		this.datatypeShow = datatypeShow;
	}
	public boolean isNullableShow() {
		return isNullableShow;
	}
	public void setNullableShow(boolean isNullableShow) {
		this.isNullableShow = isNullableShow;
	}
	
}
