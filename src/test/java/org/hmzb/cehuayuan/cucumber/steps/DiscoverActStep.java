package org.hmzb.cehuayuan.cucumber.steps;

import org.hmzb.cehuayuan.springmvc.test.AbstractSpringMvcTest;
import org.mockito.internal.matchers.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import cucumber.api.java.zh_cn.假设;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;

/**
 * .
 * 
 * @author ♨zhufu
 * @version 2014年5月16日 下午4:25:28
 */
public class DiscoverActStep extends AbstractSpringMvcTest {

	/**
	 * logger.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DiscoverActStep.class);

	private MockMvc mvc;

	private ResultActions ra;

	@假设("^我进入了huodong页面$")
	public void 我进入了huodong页面() throws Throwable {
		mvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilter(new CharacterEncodingFilter(), "/*").build();
	}

	@当("^我点击探索活动$")
	public void 我点击探索活动() throws Throwable {
		String urlTemplate = "/huodong/autoDiscover";
		ra = mvc.perform(MockMvcRequestBuilders.post(urlTemplate));
	}

	@那么("^应该探测出未开放或者未创建的两个活动地址$")
	public void 应该探测出未开放或者未创建的两个活动地址() throws Throwable {
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(MockMvcResultMatchers.jsonPath("$.url", NotNull.NOT_NULL));
	}

}
