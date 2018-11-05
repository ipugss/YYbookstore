package com.tedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tedu.been.Book;
import com.tedu.client.LoginServlet;
import com.tedu.utils.JDBCUtils;

public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("AutoLoginFilter.doFilter()....");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession sessionHttpSession = req.getSession(false);
		if (sessionHttpSession != null) {
			String username = (String) sessionHttpSession.getAttribute("username");
			if (username != null) {
				chain.doFilter(req, res);
				return;
			} else {
				AutoLogin(req);
			}

		} else {
			AutoLogin(req);
		}
		chain.doFilter(req, res);

	}

	private boolean AutoLogin(HttpServletRequest req) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Cookie[] cookies = req.getCookies();
		String username = null;
		String password = null;
		String[] cars = null;
		if (cookies != null)
			for (Cookie c : cookies) {
				if ("username".equals(c.getName())) {
					username = c.getValue();
				}
				if ("password".equals(c.getName())) {
					password = c.getValue();
				}
				if ("car".equals(c.getName())) {
					cars = URLDecoder.decode(c.getValue(), "utf-8").split("&");
					System.out.println(Arrays.toString(cars).replace("[", "(").replace("]", ")"));

					try {
						Connection connection = JDBCUtils.getConn();
						Statement statement = connection.createStatement();
						ResultSet rSet = statement.executeQuery("select * from book_info where book_id in"
								+ Arrays.toString(cars).replace("[", "(").replace("]", ")"));
						ArrayList<Book> list = new ArrayList<Book>();
						while (rSet.next()) {
							Book book = new Book(rSet.getLong("book_id"), rSet.getString("book_name"),
									rSet.getString("book_author"), rSet.getDouble("book_price"),
									rSet.getString("book_img_url"), rSet.getInt("book_now_num"),
									rSet.getInt("book_out_num"));
							list.add(book);
						}
						HttpSession session = req.getSession();
						session.setAttribute("car", list);

						rSet.close();
						statement.close();
						connection.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}

		if (!"".equals(username) && !"".equals(password)) {
			int flag = LoginServlet.findData(username, password);
			if (flag == 1) {
				req.getSession().setAttribute("username", username);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
