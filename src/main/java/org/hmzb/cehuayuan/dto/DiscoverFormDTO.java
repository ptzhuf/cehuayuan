package org.hmzb.cehuayuan.dto;

import java.io.Serializable;
import java.util.List;

/**
 * .
 * 
 * @author ♨zhufu
 * @version 2014年5月20日 下午4:56:06
 */
public class DiscoverFormDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8040147371511348451L;
	/**
	 * url 列表.
	 */
	private List<String> urlList;

	/**
	 * cookie.
	 */
	private String cookie;
	/**
	 * 是否自动报名.
	 */
	private Boolean isAutoSignUp;

	/**
	 * @param urlList
	 * @param cookie
	 * @param isAutoSignUp
	 */
	public DiscoverFormDTO(List<String> urlList, String cookie,
			Boolean isAutoSignUp) {
		this.urlList = urlList;
		this.cookie = cookie;
		this.isAutoSignUp = isAutoSignUp;
	}

	/**
	 * 
	 */
	public DiscoverFormDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the urlList
	 */
	public List<String> getUrlList() {
		return urlList;
	}

	/**
	 * @param urlList
	 *            the urlList to set
	 */
	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	/**
	 * @return the cookie
	 */
	public String getCookie() {
		return cookie;
	}

	/**
	 * @param cookie
	 *            the cookie to set
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	/**
	 * @return the isAutoSignUp
	 */
	public Boolean getIsAutoSignUp() {
		return isAutoSignUp;
	}

	/**
	 * @param isAutoSignUp
	 *            the isAutoSignUp to set
	 */
	public void setIsAutoSignUp(Boolean isAutoSignUp) {
		this.isAutoSignUp = isAutoSignUp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DiscoverFormDTO [urlList=");
		builder.append(urlList);
		builder.append(", cookie=");
		builder.append(cookie);
		builder.append(", isAutoSignUp=");
		builder.append(isAutoSignUp);
		builder.append("]");
		return builder.toString();
	}
}
