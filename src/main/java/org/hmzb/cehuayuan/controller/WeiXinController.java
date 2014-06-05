package org.hmzb.cehuayuan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.hmzb.cehuayuan.dto.HuodongDTO;
import org.hmzb.cehuayuan.weixin.WeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * .
 * 
 * @author ♨zhufu
 * @version 2014年6月5日 下午3:32:45
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController {

	/**
	 * logger.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WeiXinController.class);
	@Autowired
	private WeixinService weixinService;

	@RequestMapping(method = RequestMethod.GET)
	public String goIndex() {
		LOGGER.debug("有人访问啦~");
		return "huodong/weixin";
	}

	@ResponseBody
	@RequestMapping("/reply")
	public HuodongDTO signup(String cookie, String content)
			throws ClientProtocolException, UnsupportedEncodingException,
			IOException {
		HuodongDTO huodong = weixinService.reply(cookie, content);
		return huodong;
	}
}
