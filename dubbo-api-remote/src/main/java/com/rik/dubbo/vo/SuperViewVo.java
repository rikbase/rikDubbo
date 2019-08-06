package com.rik.dubbo.vo;

import java.io.Serializable;

public class SuperViewVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String supDataStr1 = "";
	private String supDataStr2 = "";
	private String supDataStr3 = "";
	private String supDataStr4 = "";
	private String supDataStr5 = "";
	private String columnStr = "";
	private String columnDetailStr = "";
	private String searchText = "";
	private String error;
	private String flag;
	private String order;
	private String group;
	private boolean forUpdate = false;
	private String startDate;
	private String endDate;
	private String toStatus;
	private boolean isAuto = false;
	private boolean paging;
	private int page = 1;
	private int limit = 20;
	private int startRow;
	private int count;
	private boolean isTotal = true;
	private boolean check = false;
	
	public SuperViewVo() {
		super();
	}

	public String getSupDataStr1() {
		return supDataStr1;
	}

	public void setSupDataStr1(String supDataStr1) {
		this.supDataStr1 = supDataStr1;
	}

	public String getSupDataStr2() {
		return supDataStr2;
	}

	public void setSupDataStr2(String supDataStr2) {
		this.supDataStr2 = supDataStr2;
	}

	public String getSupDataStr3() {
		return supDataStr3;
	}

	public void setSupDataStr3(String supDataStr3) {
		this.supDataStr3 = supDataStr3;
	}

	public String getSupDataStr4() {
		return supDataStr4;
	}

	public void setSupDataStr4(String supDataStr4) {
		this.supDataStr4 = supDataStr4;
	}

	public String getSupDataStr5() {
		return supDataStr5;
	}

	public void setSupDataStr5(String supDataStr5) {
		this.supDataStr5 = supDataStr5;
	}

	public String getColumnStr() {
		return columnStr;
	}

	public void setColumnStr(String columnStr) {
		this.columnStr = columnStr;
	}

	public String getColumnDetailStr() {
		return columnDetailStr;
	}

	public void setColumnDetailStr(String columnDetailStr) {
		this.columnDetailStr = columnDetailStr;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isForUpdate() {
		return forUpdate;
	}

	public void setForUpdate(boolean forUpdate) {
		this.forUpdate = forUpdate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getToStatus() {
		return toStatus;
	}

	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}

	public boolean isAuto() {
		return isAuto;
	}

	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}

	public boolean isPaging() {
		return paging;
	}

	public void setPaging(boolean paging) {
		this.paging = paging;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isTotal() {
		return isTotal;
	}

	public void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
}
