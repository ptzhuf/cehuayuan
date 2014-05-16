package org.hmzb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.hmzb.http.HmzbResponse;

public final class HttpClientUtil {
	/**
	 * 工具类不允许实例化.
	 */
	private HttpClientUtil() {
	}

	/**
	 * 日志.
	 */
	private static final Log log = LogFactory.getLog(HttpClientUtil.class);

	/**
	 * 执行请求并获得结果.
	 * 
	 * @param httpClient
	 *            httpClient
	 * @param request
	 *            请求（get or post）
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws UnsupportedEncodingException
	 */
	public static HmzbResponse getResult(HttpClient httpClient,
			HttpUriRequest request) throws IOException,
			ClientProtocolException, UnsupportedEncodingException {
		HmzbResponse hr = null;
		HttpResponse res;
		StringBuilder sb = new StringBuilder();
		synchronized (httpClient) {
			res = httpClient.execute(request);
			int statusCode = res.getStatusLine().getStatusCode();
			log.info("status code : " + statusCode);

			// if (statusCode == 200) {
			HttpEntity entity = res.getEntity();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"));

			String len = "";
			while ((len = reader.readLine()) != null) {
				sb.append(len);
			}
			// }
			hr = new HmzbResponse(statusCode, sb.toString());
		}
		
		return hr;
	}

}
