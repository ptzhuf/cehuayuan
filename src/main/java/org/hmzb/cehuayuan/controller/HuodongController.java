/**
 * 
 */
package org.hmzb.cehuayuan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.hmzb.cehuayuan.constant.DefaultContext;
import org.hmzb.cehuayuan.dto.DiscoverFormDTO;
import org.hmzb.cehuayuan.dto.HuodongDTO;
import org.hmzb.cehuayuan.dto.SignUpForm;
import org.hmzb.cehuayuan.service.HuodongService;
import org.hmzb.util.HttpServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(value = "/huodong")
public class HuodongController {

	/**
	 * 日志.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(HuodongController.class);

	@Autowired
	private HuodongService huodongService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage() {
		return "huodong/huodong";
	}

	/**
	 * 探索活动是否开启,并且决定是否自动报名.
	 * 
	 * @param url
	 *            活动url
	 * @param cookie
	 *            cookie
	 * @param request
	 *            request
	 * @param isAutoSignUp
	 *            是否自动报名活动
	 * @param request
	 *            request
	 * @return huodongDTO
	 * @throws IOException
	 *             IOException
	 * @throws UnsupportedEncodingException
	 *             UnsupportedEncodingException
	 * @throws ClientProtocolException
	 *             ClientProtocolException
	 */
	@RequestMapping(value = "/discover")
	public @ResponseBody
	List<HuodongDTO> discover(DiscoverFormDTO discoverForm, HttpServletRequest request)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException {
		List<String> urlList = discoverForm.getUrlList();
		String cookie = discoverForm.getCookie();
		Boolean isAutoSignUp = discoverForm.getIsAutoSignUp();
		// 如果cookie参数为空，则从request中 获取cookie
		if (StringUtils.isBlank(cookie)) {
			cookie = getCookie(request);
		}
		logger.info("访客IP：{} , 访客cookie: {} , 访问URL: {}", new Object[] {
				HttpServletUtil.getIpAddr(request), cookie, urlList });
		List<HuodongDTO> huodongDTOList = huodongService.discoverAll(urlList, cookie,
				isAutoSignUp);
		return huodongDTOList;
	}

	/**
	 * 自动探索即将开放的活动url.
	 * 
	 * @param huodongListUrl
	 *            活动列表的获取URL，默认为健身活动列表
	 * @param cookie
	 *            cookie
	 * @return 包含即将开放的活动url的huodongDTO
	 * @throws IOException
	 *             IOException
	 * @author zhufu 2013 2013-5-10 上午10:32:40 动作:新建
	 */
	@RequestMapping(value = "/autoDiscover")
	public @ResponseBody
	List<HuodongDTO> autoDiscover(String huodongListUrl, String cookie)
			throws IOException {
		List<HuodongDTO> huodongDTOList = huodongService.autoDiscover(
				huodongListUrl, cookie);
		return huodongDTOList;
	}

	/**
	 * 自动报名.
	 * 
	 * @param url
	 *            活动url
	 * @param cookie
	 *            cookie
	 * @param suf
	 *            报名表单
	 * @return 提示信息.报名成功或者失败
	 * @author zhufu 2013 2013-5-14 下午4:33:54 动作:新建
	 * @throws IOException
	 *             IOException
	 * @throws UnsupportedEncodingException
	 *             UnsupportedEncodingException
	 * @throws ClientProtocolException
	 *             ClientProtocolException
	 */
	@RequestMapping(value = "/autoSignUp")
	public @ResponseBody
	String autoSignUp(String url, String cookie, SignUpForm suf,
			HttpServletRequest request, HttpServletResponse response)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException {
		String message = null;
		String ip = HttpServletUtil.getIpAddr(request);

		// 如果cookie参数为空，则从request中 获取cookie
		if (StringUtils.isBlank(cookie)) {
			cookie = getCookie(request);
		}

		huodongService.autoSignUp(url, cookie, suf, ip);
		message = huodongService.checkSignUpStatus(url, cookie, ip);
		return message;
	}

	/**
	 * 如果cookie是空的就通过此方法获取cookie中的策花园cookie
	 * 
	 * @param request
	 *            request
	 * @return cookie
	 * @author zhufu 2013 2013-5-24 上午10:51:43 动作:新建
	 */
	@SuppressWarnings("deprecation")
	private String getCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String cookieValue = null;
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookieTemp = cookies[i];
			if (cookieTemp.getName().equalsIgnoreCase(
					DefaultContext.CEHUAYUAN_COOKIE_NAME)) {
				cookieValue = cookieTemp.getValue();
				if (!StringUtils.isBlank(cookieValue)) {
					cookieValue = cookieValue.trim();
					return URLDecoder.decode(cookieValue);
				}
			}
		}
		return null;
	}

	/**
	 * 测试json直接返回字符串会不会乱码.
	 * 
	 * @param userName
	 *            用户名
	 * @return 用户名
	 */
	@RequestMapping(value = "/testString")
	public @ResponseBody
	List<String> testString(ArrayList<String> userName) {
		return userName;
	}
}
