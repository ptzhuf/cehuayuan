/**
 * 北京畅游时空软件技术有限公司福州分公司 - 版权所有
 * 2013-5-8 下午6:14:25
 */
package org.hmzb.cehuayuan.constant;

/**
 * 提供默认的一些参数.
 * 
 * @author zhufu
 * 
 */
public final class DefaultContext {
	private DefaultContext() {
	}

	/**
	 * 放于185下的cookie中的策花园的cookie的cookie名称.
	 */
	public static final String CEHUAYUAN_COOKIE_NAME = "huodong_cehuayuan_cookie";

	/**
	 * 默认的cookie.
	 */
	public static final String COOKIE = ".SPBForms=8BF9E561D773AF00A1371C3EAC2E95D4EA435DE0513A80AADC706EE0A83FC4485396623C81A3254A5FD58CCDEEDDDAB51CB1C87B5BB8F4B301CB216B558474B6FF93DB812B27043A;";

	/**
	 * 策花园的IP.
	 */
	public static final String CEHUAYUAN_HOST = "http://10.5.17.74";

	/**
	 * 活动url前缀.
	 */
	public static final String HUODONG_URL_PRE = CEHUAYUAN_HOST
			+ "/whatEvents.aspx/T-";

	/**
	 * 默认的活动初始url.
	 */
	public static final String URL = HUODONG_URL_PRE + "153";

	/**
	 * 健身活动列表url.
	 */
	public static final String HUODONG_LIST_URL = CEHUAYUAN_HOST
			+ "/c/musle/whatEvents.aspx/Control_ListEvents?status=Published&_=1368077829203";

	/**
	 * 默认报名URL前缀.
	 */
	public static final String SIGN_UP_URL_PRE = CEHUAYUAN_HOST
			+ "/whatEvents.aspx/RegisteredUserSignUp?eventID=";

	/**
	 * 默认活动参与状态查询URL.
	 */
	public static final String CONTROL_EVENT_SIGN_UP_URL_PRE = CEHUAYUAN_HOST
			+ "/whatEvents.aspx/Control_EventSignUp?eventID=";

	/**
	 * 查看活动成员列表.一页20个人,超过一页的pageIndex要递增.如果不传入pageIndex则获得整个框架页面,带有第一页的参与成员.
	 */
	public static final String LIST_EVENT_MEMBERS_URL = CEHUAYUAN_HOST
			+ "/whatEvents.aspx/Controls_ListEventMembers?eventID=#{eventID}&pageIndex=#{pageIndex}";

	/**
	 * 退出活动的url,#{}作为参数占位符.(居然有权限可以把别人退出活动。。。坑啊。。。)
	 */
	public static final String EXIT_HUODONG_URL_PRE = CEHUAYUAN_HOST
			+ "/ExitEvent?userID=#{userID}&eventID=#{eventID}";
}
