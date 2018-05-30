package com.netbot.form.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.netbot.form.domain.Domain;

public class CheckContactForm {
	
	/**
	 * Assuming the contact form and captcha will be in the same form.
	 * 
	 * @param URL to be verified for contact link
	 * @param domain entity object to be updated with contact form and captcha status.
	 * 
	 * @return the updated Domain entity object.
	 */
	public Domain updateFormStatus(Domain domain) {
		Document d = null;
		try {
			//d = Jsoup.connect(domain.getContactPage()).get();
			d = Jsoup.connect(domain.getContactPage())
				    .header("Accept-Encoding", "gzip, deflate")
				    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
				    .maxBodySize(0)
				    .timeout(600000)
				    .get();
			domain.setContactLink(true);
		} catch (IOException e) {
			System.err.println("For '" + domain.getContactPage() + "': " + e.getMessage());
		}
		
		for (Element element : d.getElementsByTag("form")) {
			//System.out.println(element.toString());
			int elementCount = 0;

			Elements elements = element.getElementsContainingOwnText("name");
			if (elements != null && elements.size() > 0) {
				elementCount++;
				elements = null;
			}

			elements = element.getElementsContainingOwnText("mail");
			if (elements != null && elements.size() > 0) {
				elementCount++;
				elements = null;
			}

			elements = element.getElementsContainingOwnText("subject");
			if (elements != null && elements.size() > 0) {
				elementCount++;
				elements = null;
			}

			elements = element.getElementsContainingOwnText("message");
			if (elements != null && elements.size() > 0) {
				elementCount++;
				elements = null;
			}

			elements = element.getElementsContainingOwnText("send");
			if (elements != null && elements.size() > 0) {
				elementCount++;
			}

			elements = element.getElementsContainingOwnText("submit");
			if (elements != null && elements.size() > 0) {
				elementCount++;
			}

			elements = element.getElementsContainingOwnText("captcha");
			if (elements != null && elements.size() > 0) {
				domain.setCaptcha(true);
				elements = null;
			}

			elements = element.getElementsContainingOwnText("robot");
			if (elements != null && elements.size() > 0) {
				domain.setCaptcha(true);
				elements = null;
			}
			
			if( elementCount > 1 ) {
				// If the fields in the form like name, mail etc.. is more than 1,
				// assume availability for form and update it to true.
				domain.setContactForm(true);
				break;
			}

		}

		return domain;
	}
	
	@SuppressWarnings("unused")
	private void getPageLinks(String baseURL, String crawlURL, int depth) {
		final int MAX_DEPTH = 2;
		HashSet<String> links = new HashSet<>();
		List<String> contacts = new ArrayList<>();
		
		if ((!links.contains(crawlURL) && (depth < MAX_DEPTH))) {
			//System.out.println(">> Depth: " + depth + " [" + crawlURL + "]");
			try {
				links.add(crawlURL);

				if(Pattern.compile(Pattern.quote("contact"), Pattern.CASE_INSENSITIVE).matcher(crawlURL).find()) {
					contacts.add(crawlURL);
				}
					
				Document document = Jsoup.connect(crawlURL).get();
				Elements linksOnPage = document.select("a[href]");

				depth++;
				for (Element page : linksOnPage) {
					
					// Crawl only for the given domain and not the external domain
					if(page.attr("abs:href").contains(baseURL)) {
						getPageLinks(baseURL, page.attr("abs:href"), depth);
					}
				}
			} catch (IOException e) {
				System.err.println("For '" + crawlURL + "': " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		CheckContactForm object = new CheckContactForm();
		
		Domain domain = new Domain();
		//domain.setContactPage("http://www.mkyong.com/contact-mkyong/");
		domain.setContactPage("https://www.wipro.com/contact-wipro/");
		object.updateFormStatus(domain);
	}
}
