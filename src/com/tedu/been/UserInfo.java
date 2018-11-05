package com.tedu.been;

import javax.servlet.http.HttpSession;

public class UserInfo {

	private String username;
	private HttpSession session;

	public UserInfo(String username, HttpSession session) {
		super();
		this.username = username;
		this.session = session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

}
