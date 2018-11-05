package com.tedu.client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tedu.utils.JDBCUtils;

/**
 * �����û���¼
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginstatus = "[{\"loginstatus\":\"true\"},{\"username\":\"" + username + "\"}]";
		int flag = findData(username, password);
		if (flag == 0) {
			request.setAttribute("loginstatus", "false");
			loginstatus = "[{\"loginstatus\":\"false\"},{\"username\":\"" + username + "\"}]";
			response.getWriter().write(loginstatus);

		} else if (flag == 1) {
			Cookie user_cookie = new Cookie("username", username);
			Cookie pass_cookie = new Cookie("password", password);
			response.addCookie(user_cookie);
			response.addCookie(pass_cookie);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			response.getWriter().write(loginstatus);
			// request.setAttribute("username", username);
			/*
			 * response.getWriter().flush(); response.getWriter().close();
			 * return;
			 */
		} else if (flag == 2) {
			request.setAttribute("loginstatus", "false");
			loginstatus = "[{\"loginstatus\":\"err\"}]";
			response.getWriter().write(loginstatus);
		}
		response.getWriter().flush();
		response.getWriter().close();
	}

	public static int findData(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "select * from user_info where user_name=? and user_pw=password(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				return 1;

			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}