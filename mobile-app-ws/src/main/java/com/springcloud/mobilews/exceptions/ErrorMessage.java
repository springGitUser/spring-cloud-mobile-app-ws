package com.springcloud.mobilews.exceptions;

import java.util.Date;

public class ErrorMessage {

	private String errMsg;
	private Date timestamp;

	public ErrorMessage(String localizedMessage, Date date) {
		this.errMsg = localizedMessage;
		this.timestamp = date;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
