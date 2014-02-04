package com.redfin.sitemapgenerator;

/**
 * Access tag used in Google News Sitemap
 * 
 * The <genres> tag specifies one or more properties for an article, 
 * namely, whether it is a press release, a blog post, an opinion, 
 * an op-ed piece, user-generated content, or satire.
 * 
 * @author Bryan Campbell
 * @see <a href="http://www.google.com/support/news_pub/bin/answer.py?answer=74288">Creating a News Sitemap</a>
 * @see <a href="https://support.google.com/news/publisher/answer/93992">Content types - News (publishers) Help</a>
 *
 */
public enum NewsGenre {
    PressRelease("an official press release", true),
    Satire("an article which ridicules its subject for didactic purposes", true),
    Blog("any article published on a blog, or in a blog format", true),
    OpEd("an opinion-based article which comes specifically from the Op-Ed section of your site", false),
    Opinion("any other opinion-based article not appearing on an Op-Ed page, i.e., reviews, interviews, etc", false),
    UserGenerated("newsworthy user-generated content which has already gone through a formal editorial review process on your site.", false);
    
    private String description;
    private boolean visible;
    
    NewsGenre(String description, boolean visible) {
    	this.description = description;
    	this.visible = visible;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public boolean isVisible() {
    	return visible;
    }
}