package com.tedu.client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tedu.utils.JDBCUtils;

import net.sf.json.JSONObject;

@WebServlet("/CarIn")
public class CarIn extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 
	 * @author CQ
	 * 
	 * @time 2018/8/31
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		String car = request.getParameter("car");

		System.out.println(car);

		try {
			Connection connection = JDBCUtils.getConn();
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery("select * from book_info where book_id=" + car);
			ResultSetMetaData rdData = rSet.getMetaData();
			int columnCount = rdData.getColumnCount();
			JSONObject object = new JSONObject();
			if (rSet.next()) {

				for (int i = 1; i <= columnCount; i++) {
					String columnName = rdData.getColumnName(i);
					String columnValue = rSet.getString(columnName);
					object.put(columnName, columnValue);
				}
			}
			System.out.println(object.toString());
			response.getWriter().println(object.toString());
			response.getWriter().flush();
			response.getWriter().close();
			rSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
