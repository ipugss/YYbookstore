package com.tedu.been;

public class HotBook {

	private String src;
	private String alt;
	private String href;

	public HotBook(String src, String alt, String href) {
		this.src = src;
		this.alt = alt;
		this.href = href;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
