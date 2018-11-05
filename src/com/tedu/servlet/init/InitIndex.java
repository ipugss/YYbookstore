package com.tedu.servlet.init;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.tomcat.util.buf.UDecoder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.tedu.been.Book;
import com.tedu.been.HotBook;
import com.tedu.utils.JDBCUtils;

/**
 * 
 * @time 2018/8/27 21:12
 * @author CQ
 * @version 1.0
 * @function 初始化首页信息，将数据保存到ServletContext域
 *
 */

public class InitIndex implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		/**
		 * 
		 * 首页初始化
		 * 
		 */

		SAXReader saxreader = null;
		Element root = null;
		Document dom = null;
		String path = null;
		FileInputStream fileInputStream = null;
		try {
			saxreader = new SAXReader();
			path = arg0.getServletContext().getResource("/config/index.xml").getPath();
			fileInputStream = new FileInputStream(UDecoder.URLDecode(path));
			dom = saxreader.read(fileInputStream);
			root = dom.getRootElement();
		} catch (DocumentException e1) {
			System.out.println("文件未找到或文档有错误");
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/**
		 * 
		 * web应用部署后执行，读取config/index.xml，将数据写到ServletContext
		 * 
		 **/
		ServletContext servletContext = arg0.getServletContext();

		/**
		 * 
		 * 以下为轮播图
		 * 
		 */
		ArrayList<HotBook> hot_book = new ArrayList<HotBook>();
		List<Element> hotBooks = root.element("title_img").elements("item");
		for (Element e : hotBooks) {
			String srcString = e.attributeValue("src");
			String altString = e.attributeValue("alt");
			String hrefString = e.attributeValue("href");
			HotBook item = new HotBook(srcString, altString, hrefString);
			hot_book.add(item);
		}
		servletContext.setAttribute("hot_book", hot_book);

		Connection connection = null;
		Statement statement = null;
		try {
			connection = JDBCUtils.getConn();
			statement = connection.createStatement();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		/**
		 * 
		 * 以下为热搜
		 * 
		 */
		HashMap<String, ArrayList<Book>> hot_map = new HashMap<String, ArrayList<Book>>();

		List<Element> hot_items = root.elements("hot_maping");
		ArrayList<Book> hot_list = null;
		for (Element e : hot_items) {

			hot_list = new ArrayList<Book>();
			String title = e.attributeValue("title"); // 标题

			List<Element> items = e.elements("item");
			for (Element ei : items) {
				Book book = modifyInfo(ei, statement);
				hot_list.add(book);
			}
			hot_map.put(title, hot_list);
		}

		servletContext.setAttribute("hot_map", hot_map); // 把信息放入ServletContext内
		/**
		 * 
		 * 以下为新书
		 * 
		 */
		HashMap<String, ArrayList<Book>> title_map = new HashMap<String, ArrayList<Book>>();

		List<Element> title_items = root.elements("title_maping");
		ArrayList<Book> title_list = null;
		for (Element e : title_items) {

			title_list = new ArrayList<Book>();
			String title = e.attributeValue("title"); // 标题

			List<Element> items = e.elements("item");
			for (Element ei : items) {
				Book book = modifyInfo(ei, statement);
				title_list.add(book);
			}
			title_map.put(title, title_list);
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		servletContext.setAttribute("title_map", title_map); // 把信息放入ServletContext内

	}

	private Book modifyInfo(Element ei, Statement statement) {
		long book_id = Long.parseLong(ei.element("book_id").getTextTrim());
		String book_name = null;
		String book_author = null;
		double book_price = 0.0;
		String book_img_url = null;
		int book_now_num = 0;
		int book_out_num = 0;

		if (statement != null) {
			try {

				ResultSet rs = statement.executeQuery("select * from book_info where book_id=" + book_id);
				if (rs.next()) {
					book_name = rs.getString("book_name");
					book_author = rs.getString("book_author");
					book_price = rs.getDouble("book_price");
					book_img_url = rs.getString("book_img_url");
					book_now_num = rs.getInt("book_now_num");
					book_out_num = rs.getInt("book_out_num");

				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return new Book(book_id, book_name, book_author, book_price, book_img_url, book_now_num, book_out_num);
	}

}
