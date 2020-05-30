package net.sppan.base.vo;

public class OpLogVO {
	private Integer state;
	private String region;
	private String square;
	private String createUser;
	private String updateUser;
	private String eventname;
	private String[] createTime;
	private String[] updateTime;

	private int pageSize = 10;
	private int pageNumber = 1;
	private String searchText;
	private String sortName;
	private String sortOrder = "asc";

	OpLogVO() {
	}

	@Override
	public String toString() {
		return "OpLogVO {" + "state=" + state + ", region=" + region + ", square=" + square + ", createUser="
			+ createUser + ", updateUser=" + updateUser + ", eventname=" + eventname + ", createTime=" + createTime
			+ ", updateTime=" + updateTime + ", pageSize=" + pageSize + ", pageNumber=" + pageNumber
			+ ", searchText=" + searchText + ", sortName=" + sortName + ", sortOrder=" + sortOrder + "}";
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer value) {
		this.state = value;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String value) {
		this.region = value;
	}

	public String getSquare() {
		return this.square;
	}

	public void setSquare(String value) {
		this.square = value;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String value) {
		this.createUser = value;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String value) {
		this.updateUser = value;
	}

	public String getEventname() {
		return this.eventname;
	}

	public void setEventname(String value) {
		this.eventname = value;
	}

	public String[] getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String[] value) {
		this.createTime = value;
	}

	public String[] getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String[] value) {
		this.updateTime = value;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int value) {
		this.pageSize = value;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int value) {
		this.pageNumber = value;
	}

	public String getSearchText() {
		return this.searchText;
	}

	public void setSearchText(String value) {
		this.searchText = value;
	}

	public String getSortName() {
		return this.sortName;
	}

	public void setSortName(String value) {
		this.sortName = value;
	}

	public String getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(String value) {
		this.sortOrder = value;
	}
}
