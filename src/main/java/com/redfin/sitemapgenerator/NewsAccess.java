package com.redfin.sitemapgenerator;

/**
 * Access tag used in Google News Sitemap
 * 
 * The <access> tag specifies whether an article is available to all readers, 
 * or only to those with a free or paid membership to your site.
 * 
 * @author Bryan Campbell
 * @see <a href="http://www.google.com/support/news_pub/bin/answer.py?answer=74288">Creating a News Sitemap</a>
 * @see <a href="https://support.google.com/news/publisher/answer/93992">Content types - News (publishers) Help</a>
 */
public enum NewsAccess {
	Subscription, Registration;
}
