/**
 * 北京畅游时空软件技术有限公司福州分公司 - 版权所有
 * 2013-5-10 上午11:37:38
 */
package org.hmzb.cehuayuan.dto;

import java.io.Serializable;

/**
 * @author zhufu
 * 
 */
public class SignUpForm implements Serializable {

	/**
	 * 序列号.
	 */
	private static final long serialVersionUID = -2602088629325463904L;

	/**
	 * 邮箱(必填).
	 */
	private String email;
	/**
	 * 电话.
	 */
	private String telephone;
	/**
	 * 手机号.
	 */
	private String moblie;

	/**
	 * 内容（必填）.
	 */
	private String body;

	/**
	 * 携带人数.
	 */
	private Integer bringCount = 0;

	/**
	 * 备注.
	 */
	private String remark;

	/**
	 * 不知道是干嘛的.
	 */
	private Integer nyroModal = 1;

	/**
	 * 默认构造函数.
	 */
	public SignUpForm() {
	}

	/**
	 * 全参构造函数.
	 * 
	 * @param email
	 * @param moblie
	 * @param body
	 * @param bringCount
	 * @param remark
	 * @param nyroModal
	 */
	public SignUpForm(String email, String moblie, String telephone,
			String body, Integer bringCount, String remark, Integer nyroModal) {
		this.email = email;
		this.moblie = moblie;
		this.telephone = telephone;
		this.body = body;
		this.bringCount = bringCount;
		this.remark = remark;
		this.nyroModal = nyroModal;
	}

	/**
	 * get 邮箱(必填).
	 * 
	 * @return 邮箱(必填).
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set 邮箱(必填).
	 * 
	 * @param email
	 *            邮箱(必填).
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * get 手机号.
	 * 
	 * @return 手机号.
	 */
	public String getMoblie() {
		return moblie;
	}

	/**
	 * set 手机号.
	 * 
	 * @param moblie
	 *            手机号.
	 */
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	/**
	 * get 内容（必填）.
	 * 
	 * @return 内容（必填）.
	 */
	public String getBody() {
		return body;
	}

	/**
	 * set 内容（必填）.
	 * 
	 * @param body
	 *            内容（必填）.
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * get 携带人数.
	 * 
	 * @return 携带人数.
	 */
	public Integer getBringCount() {
		return bringCount;
	}

	/**
	 * set 携带人数.
	 * 
	 * @param bringCount
	 *            携带人数.
	 */
	public void setBringCount(Integer bringCount) {
		this.bringCount = bringCount;
	}

	/**
	 * get 备注.
	 * 
	 * @return 备注.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * set 备注.
	 * 
	 * @param remark
	 *            备注.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * get 不知道是干嘛的.
	 * 
	 * @return 不知道是干嘛的.
	 */
	public Integer getNyroModal() {
		return nyroModal;
	}

	/**
	 * set 不知道是干嘛的.
	 * 
	 * @param nyroModal
	 *            不知道是干嘛的.
	 */
	public void setNyroModal(Integer nyroModal) {
		this.nyroModal = nyroModal;
	}

	/**
	 * get 电话.
	 * 
	 * @return 电话.
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * set 电话.
	 * 
	 * @param telephone
	 *            电话.
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
