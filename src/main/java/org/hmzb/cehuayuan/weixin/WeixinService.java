package org.hmzb.cehuayuan.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.hmzb.cehuayuan.dto.HuodongDTO;
import org.hmzb.http.HmzbResponse;
import org.hmzb.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * .
 * 
 * @author ♨zhufu
 * @version 2014年6月5日 下午3:42:12
 */
@Service
public class WeixinService {

	/**
	 * logger.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WeixinService.class);

	/**
	 * 回复报名活动.
	 * 
	 * @param cookie
	 * @return
	 * @author ♨zhufu
	 * @version 2014年6月5日 下午3:42:31
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws ClientProtocolException
	 */
	public HuodongDTO reply(String cookie, String content) throws ClientProtocolException,
			UnsupportedEncodingException, IOException {
		HuodongDTO huodong = new HuodongDTO();
		String url = "http://wx.vland.cc/mobile.php";
		HttpClient client;
		client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		// 表单内容设置
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("act", "module"));
		formparams.add(new BasicNameValuePair("name", "sns"));
		formparams.add(new BasicNameValuePair("do", "post"));
		formparams.add(new BasicNameValuePair("id", "137"));
		formparams.add(new BasicNameValuePair("weid", "10"));
		formparams.add(new BasicNameValuePair("replyid", ""));
		formparams.add(new BasicNameValuePair("postid", "150"));
		formparams.add(new BasicNameValuePair("reply_content", content));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
				Consts.UTF_8);
		request.setEntity(entity);
		// String cookie =
		// "432c___msess=eyJvcGVuaWQiOiJvVi1KLXQ2Tmp6d1lJVFF6U1huRnhhc3Y4WXdjIiwiaGFzaCI6IjE3ZDQ0In0%3D;";
		request.addHeader("cookie", cookie);
		HmzbResponse result = HttpClientUtil.getResult(client, request);
//		String json = result.getHtml();
//		ObjectMapper mapper = new ObjectMapper();
		// 返回的格式
		// {"result":1,"msg":"\u56de\u590d\u6210\u529f!","reply":{"rid":"137","weid":"10","parentid":150,"fansid":"81","from_user":"oV-J-t6NjzwYITQzSXnFxasv8Ywc","content":"\u518d\u8bd5\u4e00\u4e0b\u3002\u3002\u3002","posttime":1401961362,"last_replytime":1401961362,"isshow":"1","replyid":"0","id":"176","isadmin":false,"avatar":"","nickname":"ptzhuf","tonickname":""},"postid":150}
		huodong.setCurrentStatus(1);
		return huodong;
	}

}
