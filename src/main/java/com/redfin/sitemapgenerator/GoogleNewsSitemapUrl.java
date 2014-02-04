package com.redfin.sitemapgenerator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

/**
 * One configurable Google News Search URL.  To configure, use {@link Options}
 * @author Dan Fabulich
 * @author Bryan Campbell
 * @see Options
 * @see <a href="http://www.google.com/support/news_pub/bin/answer.py?answer=74288">Creating a News Sitemap</a>
 */
public class GoogleNewsSitemapUrl extends WebSitemapUrl {

	private final String title;
	private final Publication publication;
	private final Date publicationDate;
	private final String keywords;
	private final String genres;
	private final String access;

	/** Options to configure Google News URLs */
	public static class Options extends AbstractSitemapUrlOptions<GoogleNewsSitemapUrl, Options> {
		private String title;
		private Publication publication;
		private Date publicationDate;
		private String keywords;
		private String genres;
		private String access;
	
		/** Specifies a title, URL, publication, and publication date (which is mandatory for Google News) */
		public Options(String title, String url, Publication publication, Date publicationDate) throws MalformedURLException {
			this(title, new URL(url), publication, publicationDate);
		}
		
		/** Specifies a title, URL, publication, and publication date (which is mandatory for Google News) */
		public Options(String title, URL url, Publication publication, Date publicationDate) {
			super(url, GoogleNewsSitemapUrl.class);
			
			if (title == null) throw new NullPointerException("title must not be null");
			if (publication == null) throw new NullPointerException("publication must not be null");
			if (publicationDate == null) throw new NullPointerException("publicationDate must not be null");
			
			this.title = title;
			this.publication = publication;
			this.publicationDate = publicationDate;
		}
		
		/** Specifies a list of comma-delimited keywords */
		public Options keywords(String keywords) {
			this.keywords = keywords;
			return this;
		}
		
		/** Specifies a list of comma-delimited keywords */
		public Options keywords(Iterable<String> keywords) {
			StringBuilder sb = new StringBuilder();
			boolean first = true;
			for (String keyword : keywords) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				sb.append(keyword);
			}
			this.keywords = sb.toString();
			return this;
		}
		
		/** Specifies a list of comma-delimited keywords */
		public Options keywords(String... keywords) {
			return keywords(Arrays.asList(keywords));
		}

		/** Specifies a list of comma-delimited genres */
		public Options genres(Iterable<NewsGenre> genres) {
			StringBuilder sb = new StringBuilder();
			boolean first = true;
			for (NewsGenre genre : genres) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				sb.append(genre.toString());
			}
			this.genres = sb.toString();
			return this;
		}
		
		/** Specifies a list of comma-delimited genres */
		public Options genres(NewsGenre... genres) {
			return genres(Arrays.asList(genres));
		}
		
		/** Specifies news access */
		public Options access(NewsAccess access) {
			this.access = access.toString();
			return this;
		}
	}
	
	/** Specifies a title, URL, publication, and publication date (which is mandatory for Google News) */
	public GoogleNewsSitemapUrl(String title, URL url, Publication publication, Date publicationDate) {
		this(new Options(title, url, publication, publicationDate));
	}
	
	/** Specifies a title, URL, publication, and publication date (which is mandatory for Google News) */
	public GoogleNewsSitemapUrl(String title, String url, Publication publication, Date publicationDate) throws MalformedURLException {
		this(new Options(title, url, publication, publicationDate));
	}

	/** Configures an URL with options */
	public GoogleNewsSitemapUrl(Options options) {
		super(options);
		title = options.title;
		publication = options.publication;
		publicationDate = options.publicationDate;
		keywords = options.keywords;
		genres = options.genres;
		access = options.access;
	}
	
	/** Retrieves the title */
	public String getTitle() {
		return title;
	}
	
	/** Retrieves the publication */
	public Publication getPublication() {
		return publication;
	}

	/** Retrieves the publication date */
	public Date getPublicationDate() {
		return publicationDate;
	}

	/** Retrieves the list of comma-delimited keywords */
	public String getKeywords() {
		return keywords;
	}
	
	/** Retrieves the list of comma-delimited genres */
	public String getGenres() {
		return genres;
	}
	
	/** Retrieves news access */
	public String getAccess() {
		return access;
	}

}
