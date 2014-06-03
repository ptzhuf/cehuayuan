/**
 * 北京畅游是空软件技术有限公司福州分公司 - 版权所有
 * 2013-5-8 下午2:35:43
 */
package org.hmzb.cehuayuan.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.hibernate.validator.constraints.NotBlank;
import org.hmzb.cehuayuan.constant.DefaultContext;
import org.hmzb.cehuayuan.constant.HuodongConstant;
import org.hmzb.cehuayuan.constant.IpConstant;
import org.hmzb.cehuayuan.dto.HuodongDTO;
import org.hmzb.cehuayuan.dto.SignUpForm;
import org.hmzb.http.HmzbResponse;
import org.hmzb.util.HttpClientUtil;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

/**
 * 活动相关服务.
 * 
 * @author zhufu
 * 
 */
@Service
@Validated
public class HuodongService {

	/**
	 * 日志.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(HuodongService.class);

	/**
	 * 探索活动是否已经开放.
	 * 
	 * @param url
	 *            活动地址
	 * @param cookie
	 *            使用的cookie
	 * @param isAutoSignUp
	 *            是否自动报名活动
	 * @author zhufu 2013 2013-5-8 下午2:41:43 动作:新建
	 * @throws IOException
	 *             IOException
	 * @throws UnsupportedEncodingException
	 *             UnsupportedEncodingException
	 * @throws ClientProtocolException
	 *             ClientProtocolException
	 */
	public HuodongDTO discover(String url, String cookie, Boolean isAutoSignUp)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException {
		if (StringUtils.isBlank(cookie)) {
			cookie = DefaultContext.COOKIE;
		}
		if (StringUtils.isBlank(url)) {
			url = DefaultContext.URL;
		}
		HuodongDTO huodongDTO = new HuodongDTO();
		HttpClient client = new DefaultHttpClient();
		client.getParams().setIntParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				20000);
		HttpUriRequest request = new HttpGet(url);
		request.addHeader("Cookie", cookie);
		// HttpResponse response = client.execute(request);
		HmzbResponse result = HttpClientUtil.getResult(client, request);
		Integer statusCode = result.getStatusCode();
		// System.out.println(result);
		if (statusCode.equals(500)) {
			logger.info("{}, 当前活动还未开放", new SimpleDateFormat(
					"yyyy年MM月dd日 hh:mm:ss").format(new Date()));
			huodongDTO.setCurrentStatus(HuodongConstant.UNOPENED);
		} else if (statusCode.equals(404)) {
			logger.info("{}, 当前活动还未创建", new SimpleDateFormat(
					"yyyy年MM月dd日 hh:mm:ss").format(new Date()));
			huodongDTO.setCurrentStatus(HuodongConstant.UNCREATE);
		} else {
			logger.info("{}, 活动开放了, url : {}", new SimpleDateFormat(
					"yyyy年MM月dd日 hh:mm:ss").format(new Date()), url);
			huodongDTO.setCurrentStatus(HuodongConstant.OPENED);
		}
		huodongDTO.setUrl(url);
		return huodongDTO;

	}

	/**
	 * 自动探索即将开启的活动URL.
	 * 
	 * @param huodongListUrl
	 *            获取活动列表的url
	 * @param cookie
	 *            cookie
	 * @return 带有活动地址的dto
	 * @author zhufu 2013 2013-5-9 下午5:20:58 动作:新建
	 * @throws Exception
	 */
	public List<HuodongDTO> autoDiscover(String huodongListUrl, String cookie)
			throws Exception {

		try {
			List<HuodongDTO> huodongDTOList = new ArrayList<HuodongDTO>();
			if (StringUtil.isBlank(huodongListUrl)) {
				huodongListUrl = DefaultContext.HUODONG_LIST_URL;
			}
			if (StringUtil.isBlank(cookie)) {
				cookie = DefaultContext.COOKIE;
			}
			// 查找近期活动列表
			Document html = Jsoup.connect(huodongListUrl)
					.header("cookie", cookie).get();
			Elements aElements = html.select("a");
			TreeSet<Integer> huodongIdSet = new TreeSet<Integer>();
			for (Element a : aElements) {
				String url = a.attr("href");
				// 如果包含活动前缀则测试活动是否开放，直到有未开放的活动
				if (url.contains(DefaultContext.HUODONG_URL_CONTAIN)) {
					// 记录url中的活动编号
					Integer huodongId = getHuodongIdFromHuodongURL(url);
					huodongIdSet.add(huodongId);
					// 探索活动
					HuodongDTO huodongDTO = discover(url, cookie, false);
					Integer status = huodongDTO.getCurrentStatus();
					if (status.equals(HuodongConstant.UNOPENED)) { // 活动未开放，则探索结束
						huodongDTOList.add(huodongDTO);
					}
				}
			}

			// 近期活动页面完全没有活动，说明活动可能被删除了,或者存在其他意外，这时可以考虑查询以往活动
			if (huodongIdSet.isEmpty()) {
				String historyHuodongListUrl = DefaultContext.HUODONG_FINISH_LIST_URL
						+ "pageIndex=1";
				html = Jsoup.connect(historyHuodongListUrl)
						.header("cookie", cookie).get();
				// 上面获取到了第一页，准备获取最后一个分页
				aElements = html.select("div.paginator").select("a");
				// 找到最后一页的页码
				Element aElement = aElements.get(aElements.size() - 2);
				Integer pageIndex = Integer.valueOf(aElement.text());
				// 查询最后一页
				html = Jsoup
						.connect(
								DefaultContext.HUODONG_FINISH_LIST_URL
										+ "pageIndex=" + pageIndex)
						.header("cookie", cookie).get();
				aElements = html.select("a");
				for (Element a : aElements) {
					String url = a.attr("href");
					// 如果包含活动前缀则测试活动是否开放，直到有未开放的活动 TODO
					if (url.contains(DefaultContext.HUODONG_URL_CONTAIN)) {
						// 记录url中的活动编号
						Integer huodongId = getHuodongIdFromHuodongURL(url);
						huodongIdSet.add(huodongId);
						// 都是历史活动，不需要再去探索了~，直接把活动ID带走就可
					}
				}
			}

			// 如果活动列表中是空的，说明没有开放的活动，这个时候就去探索还未创建的活动
			if (huodongDTOList.size() == 0) {
				// 获取上个方法中查到的活动id最大值
				Integer maxHuodongId = huodongIdSet.last();
				do {
					// 活动下一个活动的地址
					String url = new StringBuilder()
							.append(DefaultContext.HUODONG_URL_PRE)
							.append(++maxHuodongId).toString();
					// 探索下一个活动
					HuodongDTO huodongDTO = discover(url, cookie, false);
					// 如果下一个活动未创建，那么它极有可能成为健身的下一个活动，故将其加入结果集中
					if (huodongDTO.getCurrentStatus().equals(
							HuodongConstant.UNCREATE)) {
						huodongDTOList.add(huodongDTO);
					}
				} while (huodongDTOList.size() < 2);
			}
			// 返回探索到的url
			return huodongDTOList;
		} catch (Exception e) {
			String msg = "autoDiscover 出错";
			logger.error(new StringBuilder().append(msg).append(", 请求参数为: {}")
					.toString(), Arrays.deepToString(new Object[] {
					huodongListUrl, cookie }));
			logger.error(msg, e);
			throw e;
		}
	}

	/**
	 * 通过活动的url获取活动的id.
	 * 
	 * @param url
	 *            活动url
	 * @return 活动id编号
	 * @author ♨zhufu
	 * @version 2014年5月19日 下午2:50:52
	 */
	private Integer getHuodongIdFromHuodongURL(String url) {
		Integer subIndex = url.indexOf(DefaultContext.HUODONG_URL_CONTAIN)
				+ DefaultContext.HUODONG_URL_CONTAIN.length();
		// FIXME 暂时只取三位数字，也不知道健身能不能撑到破1千次活动呢，到时候再换正则匹配吧。。。
		Integer huodongId = Integer.valueOf(url.substring(subIndex,
				subIndex + 3));
		return huodongId;
	}

	/**
	 * 自动报名活动.
	 * 
	 * @param huodongUrl
	 *            活动url
	 * @param cookie
	 *            cookie
	 * @param suf
	 *            表单对象
	 * @return response
	 * @throws ClientProtocolException
	 *             ClientProtocolException
	 * @throws UnsupportedEncodingException
	 *             UnsupportedEncodingException
	 * @throws IOException
	 *             IOException
	 * @author zhufu 2013 2013-5-14 下午4:22:08 动作:新建
	 */
	public HmzbResponse autoSignUp(String huodongUrl, String cookie,
			SignUpForm suf, @NotBlank String ip)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		// 拼凑注册URL,获得request
		HttpUriRequest request = new HttpPost(getSignUpUrl(huodongUrl));
		// 过滤cookie和ip
		cookie = filterIpAndCookie(cookie, ip);
		Assert.hasText(cookie);
		request.addHeader("cookie", cookie);
		HttpParams params = createSignUpHttpParams(suf);
		request.setParams(params);
		logger.info("{}", request.getURI());
		HmzbResponse response = HttpClientUtil.getResult(client, request);
		return response;
	}

	/**
	 * 过滤ip和cookie
	 * 
	 * @param cookie
	 * @param ip
	 * @return
	 * @author zhufu 2013 2013-5-15 下午12:41:57 动作:新建
	 */
	public String filterIpAndCookie(String cookie, String ip) {
		// 如果为空则判断是否为开发者如果是，则使用默认cookie
		if (ip.equals(IpConstant.ZHU_FU)) {
			if (StringUtils.isBlank(cookie)) {
				// TODO 后续考虑加入cookies列表(ip-cookies映射)，省的开发组每次都要输入.
				cookie = DefaultContext.COOKIE;
			}
		} else {
			logger.info("ip : {}, cookie : {}", ip, cookie);
		}
		return cookie;
	}

	/**
	 * 检查报名状态.
	 * 
	 * @param huodongUrl
	 *            活动的url
	 * @param cookie
	 *            cookie
	 * @param ip
	 *            ip
	 * @return 报名状态信息.
	 * @throws IOException
	 *             IOException
	 * @author zhufu 2013 2013-5-15 下午12:49:53 动作:新建
	 */
	public String checkSignUpStatus(String huodongUrl, String cookie,
			@NotBlank String ip) throws IOException {
		String message = null;
		// 取得检查活动报名状态url
		String url = getControlEventSignUpUrl(huodongUrl);
		cookie = filterIpAndCookie(cookie, ip);
		if (StringUtils.isBlank(cookie)) {
			return "请输入cookie";
		}
		Connection connection = Jsoup.connect(url).header("cookie", cookie);
		Document html = null;
		try {
			html = connection.get();
			Element element = html.select(".tn-helper-flowfix").first();
			if (element != null) {
				message = element.text();
			} else {
				message = "活动尚未报名";
			}
		} catch (HttpStatusException e) {
			if (e.getStatusCode() == 404) {
				message = "活动尚未报名";
			}
		}
		return message;
	}

	/**
	 * 根据报名表单 创建http参数.
	 * 
	 * @param suf
	 *            SignUpForm报名表单
	 * @return http params
	 * @author zhufu 2013 2013-5-14 下午2:08:48 动作:新建
	 */
	public HttpParams createSignUpHttpParams(SignUpForm suf) {
		HttpParams params = new BasicHttpParams();
		params.setParameter("email", suf.getEmail());
		params.setParameter("moblie", suf.getMoblie());
		params.setParameter("telephone", suf.getTelephone());
		params.setParameter("body", suf.getBody());
		params.setParameter("bringCount", suf.getBringCount());
		params.setParameter("remark", suf.getRemark());
		return params;
	}

	/**
	 * 转换活动url为报名url.
	 * 
	 * @param huodongUrl
	 *            活动url
	 * @return 报名url
	 * @author zhufu 2013 2013-5-15 上午11:42:26 动作:新建
	 */
	public String getSignUpUrl(String huodongUrl) {
		String signUpUrl = null;
		signUpUrl = DefaultContext.SIGN_UP_URL_PRE + getHuodongId(huodongUrl);
		return signUpUrl;
	}

	/**
	 * 获得检查报名状态的url.
	 * 
	 * @param huodongUrl
	 *            活动url
	 * @return 检查报名状态的url
	 * @author zhufu 2013 2013-5-15 下午12:39:10 动作:新建
	 */
	public String getControlEventSignUpUrl(String huodongUrl) {
		String controlEventSignUpUrl = null;
		controlEventSignUpUrl = DefaultContext.CONTROL_EVENT_SIGN_UP_URL_PRE
				+ getHuodongId(huodongUrl);
		return controlEventSignUpUrl;
	}

	public String exitHuodong(String huodongUrl, String userId)
			throws IOException {
		// TODO 退出活动
		// 取得检查活动报名状态url
		String url = "http://10.5.17.74/whatEvents.aspx/ExitEvent?userID=5313&eventID=262";
		Document html = Jsoup.connect(url)
				.header("cookie", DefaultContext.COOKIE).get();
		logger.info("{}", html);
		return html.toString();
	}

	/**
	 * 通过活动url取得活动id.
	 * 
	 * @param huodongUrl
	 * @return 活动id
	 * @author zhufu 2013 2013-5-15 下午12:37:00 动作:新建
	 */
	public String getHuodongId(String huodongUrl) {
		return huodongUrl.substring(huodongUrl.indexOf('-') + 1);
	}

	/**
	 * 探索所有的url.
	 * 
	 * @param urlList
	 *            url列表
	 * @param cookie
	 *            cookie
	 * @param isAutoSignUp
	 *            是否自动报名
	 * @return huodong列表
	 * @author ♨zhufu
	 * @version 2014年5月20日 下午5:10:17
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws ClientProtocolException
	 */
	public List<HuodongDTO> discoverAll(List<String> urlList, String cookie,
			Boolean isAutoSignUp) throws ClientProtocolException,
			UnsupportedEncodingException, IOException {
		List<HuodongDTO> huodongDTOList = new ArrayList<HuodongDTO>();
		for (String url : urlList) {
			HuodongDTO huodongDTO = discover(url, cookie, isAutoSignUp);
			huodongDTOList.add(huodongDTO);
		}
		return huodongDTOList;
	}

}
