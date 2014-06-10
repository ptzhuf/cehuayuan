package org.hmzb.cehuayuan.pms;

import java.io.IOException;

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
 * @version 2014年6月10日 上午11:51:34
 */
@Controller
@RequestMapping("/pms")
public class PMSController {

	/**
	 * logger.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PMSController.class);
	@Autowired
	private PmsService pmsService;

	@RequestMapping(method = RequestMethod.GET)
	public String toIndex() {
		LOGGER.info("亲，你访问到了~！");
		return "pms/pms";
	}

	@ResponseBody
	@RequestMapping("/stat")
	public Double statWorkingHours(String cookie, String projectKeyWord,
			String startTime, String endTime) throws IOException {
		Double result = pmsService.statWorkingHours(cookie, projectKeyWord,
				startTime, endTime);
		return result;
	}

}
