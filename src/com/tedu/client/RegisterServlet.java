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

import com.tedu.utils.JDBCUtils;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		String username = request.getParameter("username");
		String password = request.getParameterValues("password")[0];
		int status = registerUser(username, password);
		String registerstatus = "[{\"registerstatus\":\"false\"}]";
		if (status == 1) {
			Cookie user_cookie = new Cookie("username", username);
			Cookie pass_cookie = new Cookie("password", password);
			response.addCookie(user_cookie);
			response.addCookie(pass_cookie);
			registerstatus = "[{\"registerstatus\":\"success\"}]";
		} else if (status == 0) {
			registerstatus = "[{\"registerstatus\":\"false\"}]";
		} else if (status == 2) {
			registerstatus = "[{\"registerstatus\":\"err\"}]";
		}
		response.getWriter().println(registerstatus);
		response.getWriter().flush();
		response.getWriter().close();
	}

	private int registerUser(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConn();
			String sql = "insert into user_info (user_name,user_pw) values (?,password(?))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			if (ps.executeUpdate() > 0) {

				return 1; // 状态1：注册成功
			} else {
				return 0; // 状态0：注册失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2; // 状态2：服务器故障
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}