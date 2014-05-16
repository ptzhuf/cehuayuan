package org.hmzb.http;

public class HmzbResponse {
	/**
	 * 状态码.
	 */
	private Integer statusCode;
	/**
	 * 返回的内容.
	 */
	private String html;

	/**
	 * get 状态码.
	 * @return 状态码.
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * set 状态码.
	 * @param statusCode 状态码.
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * get 返回的内容.
	 * @return 返回的内容.
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * set 返回的内容.
	 * @param result 返回的内容.
	 */
	public void setHtml(String result) {
		this.html = result;
	}

	public HmzbResponse(Integer statusCode, String html) {
		super();
		this.statusCode = statusCode;
		this.html = html;
	}

	public HmzbResponse() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HmzbResponse [statusCode=");
		builder.append(statusCode);
		builder.append(", html=");
		builder.append(html);
		builder.append("]");
		return builder.toString();
	}


}
