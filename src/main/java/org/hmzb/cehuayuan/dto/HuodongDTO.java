/**
 * 北京畅游是空软件技术有限公司福州分公司 - 版权所有
 * 2013-5-8 下午2:57:35
 */
package org.hmzb.cehuayuan.dto;

import java.io.Serializable;

/**
 * @author zhufu
 * 
 */
public class HuodongDTO implements Serializable {

	/**
	 * 序列号.
	 */
	private static final long serialVersionUID = -8839976279284873637L;
	/**
	 * 活动url.
	 */
	private String url;
	/**
	 * 当前活动状态.
	 */
	private Integer currentStatus;

	/**
	 * get 活动url.
	 * 
	 * @return 活动url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * set 活动url.
	 * 
	 * @param url
	 *            活动url.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * get 当前活动状态.
	 * 
	 * @return 当前活动状态.
	 */
	public Integer getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * set 当前活动状态.
	 * 
	 * @param currentStatus
	 *            当前活动状态.
	 */
	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HuodongDTO [url=");
		builder.append(url);
		builder.append(", currentStatus=");
		builder.append(currentStatus);
		builder.append("]");
		return builder.toString();
	}
}
