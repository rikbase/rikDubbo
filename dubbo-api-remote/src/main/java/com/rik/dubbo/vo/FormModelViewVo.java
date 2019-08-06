package com.rik.dubbo.vo;

public class FormModelViewVo extends SuperViewVo{

	private static final long serialVersionUID = 1L;
	
	private String formName;
	private String columnName;
	private String columnComment;
	private String dataType;
	private long characterMaximumLength;
	private String isNullable;
	private String datatypeShow;
	private boolean isNullableShow;
	private String SQLshell;
	private String DDL;
	
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
		if (dataType.equals("varchar")) {
			setDatatypeShow("String");
		}else if (dataType.equals("int") || dataType.equals("bigint")) {
			setDatatypeShow("Long");
		}else if (dataType.equals("decimal")) {
			setDatatypeShow("Double");
		}else if (dataType.equals("datetime")) {
			setDatatypeShow("Date");
		}
		
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
		if (isNullable.equals("YES")) {
			setNullableShow(true);
		}else {
			setNullableShow(false);
		}
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
	public String getSQLshell() {
		return SQLshell;
	}
	public void setSQLshell(String sQLshell) {
		SQLshell = sQLshell;
	}
	public String getDDL() {
		return DDL;
	}
	public void setDDL(String dDL) {
		DDL = dDL;
	}
}
