package com.tedu.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String accept = req.getHeader("Accept");
		if (accept != null && (accept.indexOf("text") >= 0 || accept.indexOf("html") >= 0)) {
			request.setCharacterEncoding("utf-8");

			response.setCharacterEncoding("utf-8");

			HttpServletRequest myRequest = new MyHttpServletRequest(req);
			chain.doFilter(myRequest, response);
		} else {
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
	}
}

class MyHttpServletRequest extends HttpServletRequestWrapper {
	private boolean isEncode = true;
	/*
	 * isEncode �Ƿ�����ֶ������ı�ʶ, Ĭ��ֵ��true, ��ʾ��û���ֶ������
	 */
	private HttpServletRequest request;

	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		return this.getParameterValues(name) == null ? null : this.getParameterValues(name)[0];
	}

	@Override
	public String[] getParameterValues(String name) {
		return this.getParameterMap().get(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		/*
		 * �÷������Է����������������ɵ�map����, ��ʱmap�� ���������������(get�ύ),
		 * ���ǿ��Ա���map�е�ÿһ�� ����ֵ, �ֶ�����봦�������ȷ�������ٴδ��map��,
		 * ��󷵻�һ��û�������map����
		 */
		if ("POST".equalsIgnoreCase(request.getMethod())) {// ��POST�ύ
			return request.getParameterMap();
		} else if ("GET".equalsIgnoreCase(request.getMethod())) {
			// ����map, ����ȡ������, �ֶ�������ٴδ��map������map
			Map<String, String[]> map = request.getParameterMap();
			if (isEncode) {
				for (Map.Entry<String, String[]> entry : map.entrySet()) {
					// ȡ��ÿһ����ֵ��, ����ֵ������, ����Ҫ����
					String[] vs = entry.getValue();
					for (int i = 0; i < vs.length; i++) {
						try {
							vs[i] = new String(vs[i].getBytes("iso8859-1"), "utf-8");
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException(e);
						}
					}
				}
				isEncode = false;
			}
			return map;
		} else {// ����5���ύ��ʽ
			return request.getParameterMap();
		}
	}
}
/*
 * ��װ����: request���� --> ��������: A ��װ������������ʵ�ֵĽӿ�:
 * HttpServletRequest
 * 
 * װ����: MyHttpServletRequest Ҫʵ��HttpServletRequest�ӿ�
 * ��������MyHttpServletRequest�̳�һ��װ����, ���������Ҳ��һ�� װ����,
 * ֻ��Ҫ������Ҫ����ĸ����з�������!!
 */
/*
 * 1.дһ��װ����, װ����Ҫ�ͱ�װ������������ʵ����ͬ�Ľӿ� ���߼̳���ͬ�ĸ���
 * 2.װ����Ҫ�ṩ���췽�����ܱ�װ���߲�����������ڲ� 3.������Ҫ����ķ���ֱ�ӽ��и���,
 * ���ڲ������ķ���, ���� ԭ�ж����ϵķ���
 */
