package com.erban.bean;

public class Msg {
	/*
	{
	    "msg": [
	        {
	            "uptime": "2015年3月11日",
	            "content": "今日系统维护,造成的不变敬请谅解",
	            "from": "系统"
	        },
	        {
	            "uptime": "2015年3月11日",
	            "content": "今日系统维护,造成的不变敬请谅解",
	            "from": "系统"
	        }
	    ],
	    "code": 0
	}
	*/
	
	private String uptime;
	private String content;
	private String from;
	
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	
}
