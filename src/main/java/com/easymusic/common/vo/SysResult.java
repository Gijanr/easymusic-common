package com.easymusic.common.vo;

/**
 * 可以把后端出现的所有信息传递给前端
 * @author Administrator
 *
 */
public class SysResult {
	//表示状态码的数字
	private Integer status;
	//携带详细信息的字符串
	private String msg;
	//携带的各种数据
	//按照业务逻辑封装各种结构，让前端做得更丰富
	private Object data;
	
	public SysResult(Integer status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static SysResult build(Integer status, String msg, Object data) {
		return new SysResult(status, msg, data);
	}
	public static SysResult ok() {
		return new SysResult(200, "ok", null);
	}
	
	public SysResult() {
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
