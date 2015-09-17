package com.techzguyz.samplesocketandroidapp.other;

public class Msg {
	private String fromName, msg;
	private boolean isSelf;
	
	public Msg() {
		
	}
	
	public Msg(String fromName, String msg, boolean isSelf) {
		this.fromName = fromName;
		this.msg = msg;
		this.isSelf = isSelf;
	}
	
	public String getFromName() {
		return fromName;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean isSelf() {
		return isSelf;
	}
	
	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

}
