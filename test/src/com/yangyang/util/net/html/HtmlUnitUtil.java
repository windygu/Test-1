package com.yangyang.util.net.html;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitUtil {

	public static void main(String[] args) {
		System.out.println(nextPage("http://www.zjzfcg.gov.cn/cggg","l-btn-icon pagination-next"));
	}
	
	public static String nextPage(String urlStr, String btnId)
	{
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		String nextPage = "";
		try {
			HtmlPage htmlPage = (HtmlPage)webClient.getPage(urlStr);
			HtmlInput input = (HtmlInput) htmlPage.getElementById(btnId);
			System.out.println(input);
			nextPage = input.click();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			webClient.close();
		}
		return nextPage;
	}
}
