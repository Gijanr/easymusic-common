package com.easymusic.common.pojo;

public class Test implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer testId; // 测试ID
	private String testName; // 测试名称
	private String info; // 测试名称
	private String other; // 测试名称

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
