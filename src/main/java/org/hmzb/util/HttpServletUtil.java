/**
 * 北京畅游时空软件技术有限公司福州分公司 - 版权所有
 * 2013-5-9 下午12:09:36
 */
package org.hmzb.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhufu
 * 
 */
public final class HttpServletUtil {
	private HttpServletUtil() {
	}

	/**
	 * 获得客户端IP.
	 * 
	 * @param request
	 *            请求request
	 * @return IP地址
	 * @author zhufu 2013 2013-5-9 下午12:10:08 动作:新建
	 */
	public static String getIpAddr(HttpServletRequest request) {
		// 确保反向代理的ip获取正确性.
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 15) { // "***.***.***.***".length()
												// = 15
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}

		return ip;
	}

}
