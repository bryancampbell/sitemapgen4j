package com.redfin.sitemapgenerator;

import java.util.Locale;

/**
 * Publication used in Google News Sitemap
 * 
 * The <publication> tag specifies the publication in which the article appears. 
 * It has two required child tags: <name> and <language>. The <name> is the name 
 * of the news publication. It must exactly match the name as it appears on your 
 * articles in news.google.com, omitting any trailing parentheticals. For example, 
 * if the name appears in Google News as "The Example Times (subscription)", you 
 * should use the name, "The Example Times". The <language> is the language of 
 * your publication. It should be an ISO 639 Language Code (either 2 or 3 
 * letters). Exception: For Chinese, please use zh-cn for Simplified Chinese or 
 * zh-tw for Traditional Chinese.
 * 
 * @author Bryan Campbell
 * @see <a href="http://www.google.com/support/news_pub/bin/answer.py?answer=74288">Creating a News Sitemap</a>
 *
 */
public class Publication {
	
	private final String name;
	private final String language;
	
	public Publication(String name, String language) {
		this.name = name;
		this.language = language;
	}
	
	public Publication(String name, Locale locale) {
		this.name = name;
		this.language = locale.getLanguage();
	}

	public String getName() {
		return name;
	}

	public String getLanguage() {
		return language;
	}
	
}
