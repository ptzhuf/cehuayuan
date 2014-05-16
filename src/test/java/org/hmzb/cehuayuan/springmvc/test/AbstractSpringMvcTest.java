package org.hmzb.cehuayuan.springmvc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * .
 * 
 * @author ♨zhufu
 * @version 2014年5月16日 下午4:26:57
 */
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class AbstractSpringMvcTest {

	@Autowired
	protected WebApplicationContext wac;
}
