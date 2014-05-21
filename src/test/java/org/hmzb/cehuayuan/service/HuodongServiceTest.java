/**
 * 北京畅游时空软件技术有限公司福州分公司 - 版权所有
 * 2013-5-9 下午5:40:43
 */
package org.hmzb.cehuayuan.service;

import java.io.IOException;

import org.hmzb.cehuayuan.constant.DefaultContext;
import org.hmzb.cehuayuan.dto.SignUpForm;
import org.hmzb.http.HmzbResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhufu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:servlet-context.xml")
public class HuodongServiceTest {

	String huodongUrl = "http://10.5.17.74/whatEvents.aspx/T-262";
	String ip = "10.5.15.185";
	/**
	 * 日志.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(HuodongServiceTest.class);

	@Autowired
	private HuodongService huodongService;

	@Test
	public void testExit() throws IOException {
		String html = huodongService.exitHuodong(null, null);
		// HttpClient client = new DefaultHttpClient();
		// // 拼凑注册URL,获得request
		// HttpUriRequest request = new HttpPost(
		// "http://10.5.17.74/whatEvents.aspx/ExitEvent");
		// request.addHeader("cookie", DefaultContext.COOKIE);
		// HttpParams params = new BasicHttpParams();
		// params.setParameter("userID", "5313");
		// params.setParameter("eventID", "262");
		// request.setParams(params);
		// HmzbResponse response = HttpClientUtil.getResult(client, request);
		// System.out.println(response.getStatusCode());
		// System.out.println(response.getHtml());
	}

	@Test
	public void testAutoDiscover() throws IOException {
		String url = huodongService.autoDiscover(null, null).get(0).getUrl();
		logger.info("探索到的Url为 : {}", url);
	}

	@Test
	public void testAutoSignUp() throws IOException {
		SignUpForm suf = createSUF();
		HmzbResponse response = huodongService.autoSignUp(huodongUrl, "", suf,
				"10.5.15.185");
		logger.info("返回结果：\n{}", response);
	}

	@Test
	public void testCheckSignUpStatus() throws IOException {
		String message = huodongService.checkSignUpStatus(huodongUrl,
				DefaultContext.COOKIE, ip);
		logger.info(message);
	}

	@Test
	public final void testLoggerObjectArray() {
		String cookie = ".sdfjl=rewjkls;";
		String url = "http://jfdslkl.djl.com";
		logger.info("访客IP：{} , 访客cookie: {} , 访问URL: {}", new Object[] {
				"123.232153.53213.32", cookie, url });
	}

	private SignUpForm createSUF() {
		String email = "123@123.com";
		String remark = "123";
		SignUpForm suf = new SignUpForm(email, null, null, null, 0, remark, 0);
		return suf;
	}

}
