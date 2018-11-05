package com.tedu.been;

public class Book {
	private long book_id;
	private String book_name;
	private String book_author;
	private double book_price;
	private String book_img_url;
	private int book_now_num;
	private int book_out_num;

	public Book(Long book_id, String book_name, String book_author, double book_price, String book_img_url,
			int book_now_num, int book_out_num) {
		this.book_id = book_id;
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_price = book_price;
		this.book_img_url = book_img_url;
		this.book_now_num = book_now_num;
		this.book_out_num = book_out_num;
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public String getBook_img_url() {
		return book_img_url;
	}

	public void setBook_img_url(String book_img_url) {
		this.book_img_url = book_img_url;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public double getBook_price() {
		return book_price;
	}

	public void setBook_price(double book_price) {
		this.book_price = book_price;
	}

	public int getBook_now_num() {
		return book_now_num;
	}

	public void setBook_now_num(int book_now_num) {
		this.book_now_num = book_now_num;
	}

	public int getBook_out_num() {
		return book_out_num;
	}

	public void setBook_out_num(int book_out_num) {
		this.book_out_num = book_out_num;
	}

	public String getbook_img_url() {
		return book_img_url;
	}

	public void setbook_img_url(String book_img_url) {
		this.book_img_url = book_img_url;
	}

}
