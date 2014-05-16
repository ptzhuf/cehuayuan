package org.hmzb.cehuayuan.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping("/csrf/test")
	public String testCsrf() {
		return "csrf/test";
	}

	@RequestMapping("/test")
	public @ResponseBody
	Map<String, String> test() throws UnsupportedEncodingException {
		String s = "afdjasl啊阶    段开\\发了";
		String unicodeStr = "afdjasl\u554A\u9636 \u6BB5\u5F00\\\u53D1\u4E86";
		byte[] bytes = s.getBytes();
		String byteS = new String(bytes, "unicode");

		String escape = StringEscapeUtils.escapeJava(s);
		Map<String, String> map = new HashMap<String, String>();
		map.put("test", byteS);
		map.put("s", s);
		map.put("unicodeStr", unicodeStr);
		map.put("escape", escape);
		map.put("mapStr", map.toString());
		System.out.println(map.toString());
		return map;
	}

	@RequestMapping("/string/test")
	public @ResponseBody
	String testString() throws UnsupportedEncodingException {
		String s = "afdjasl啊阶    段开\\发了";
		byte[] bytes = s.getBytes("unicode");
		String byteS = new String(bytes);
		System.out.println(byteS);
		String escape = StringEscapeUtils.escapeJava(s);
		return escape;
	}

}
