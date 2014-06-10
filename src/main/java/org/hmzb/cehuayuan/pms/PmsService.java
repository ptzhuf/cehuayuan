package org.hmzb.cehuayuan.pms;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * .
 * 
 * @author ♨zhufu
 * @version 2014年6月10日 下午2:00:46
 */
@Service
public class PmsService {

	/**
	 * @param cookie
	 * @param projectKeyWord
	 * @param startTime
	 * @param endTime
	 * @return
	 * @author ♨zhufu
	 * @version 2014年6月10日 下午2:00:59
	 * @throws IOException
	 */
	public Double statWorkingHours(String cookie, String projectKeyWord,
			String startTime, String endTime) throws IOException {
		Double result = null;

		String url = "http://pms.local.17173.com/task_list_department.php?action=search&employment_id=&state=0&time_id=plan&start_date="
				+ startTime + "&end_date=" + endTime + "&x=24&y=5";
		Document resultDoc = Jsoup.connect(url).header("cookie", cookie)
				.timeout(20000).get();
		Elements table = resultDoc.select("table.list");
		Elements trs = table.select("tr");
		// 移除表头
		trs.remove(0);
		// 移除最后合计
		trs.remove(trs.size() - 1);
		// 总耗时
		Double totalTime = 0d;
		String regex = projectKeyWord;// ".*支付平台.*";
		for (Element element : trs) {
			Elements tds = element.select("td");
			// System.out.println(tds);
			String projectName = tds.get(3).text();
			Double realTime = Double.valueOf(tds.get(7).text());
			if (projectName.matches(regex)) {
				totalTime += realTime;
			}
		}
		result = totalTime;
		return result;
	}

}
