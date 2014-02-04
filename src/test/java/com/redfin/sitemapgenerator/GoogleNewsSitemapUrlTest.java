package com.redfin.sitemapgenerator;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import junit.framework.TestCase;

import com.redfin.sitemapgenerator.W3CDateFormat.Pattern;

public class GoogleNewsSitemapUrlTest extends TestCase {
	
	File dir;
	GoogleNewsSitemapGenerator wsg;
	
	public void setUp() throws Exception {
		dir = File.createTempFile(GoogleNewsSitemapUrlTest.class.getSimpleName(), "");
		dir.delete();
		dir.mkdir();
		dir.deleteOnExit();
	}
	
	public void tearDown() {
		wsg = null;
		for (File file : dir.listFiles()) {
			file.deleteOnExit();
			file.delete();
		}
		dir.delete();
		dir = null;
	}
	
	public void testRequired() throws Exception {
		W3CDateFormat dateFormat = new W3CDateFormat(Pattern.SECOND);
		dateFormat.setTimeZone(W3CDateFormat.ZULU);
		wsg = GoogleNewsSitemapGenerator.builder("http://www.example.com", dir)
			.dateFormat(dateFormat).build();
		GoogleNewsSitemapUrl url = new GoogleNewsSitemapUrl.Options("Crisis Averted", "http://www.example.com/index.html", new Publication("Fox News", Locale.US), new Date(0)).build();
		wsg.addUrl(url);
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
			"<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:news=\"http://www.google.com/schemas/sitemap-news/0.9\" >\n" + 
			"  <url>\n" + 
			"    <loc>http://www.example.com/index.html</loc>\n" + 
			"    <news:news>\n" + 
			"      <news:publication>\n" + 
			"        <news:name>Fox News</news:name>\n" +
			"        <news:language>en</news:language>\n" +
			"      </news:publication>\n" + 
			"      <news:publication_date>1970-01-01T00:00:00Z</news:publication_date>\n" + 
			"      <news:title>Crisis Averted</news:title>\n" +
			"    </news:news>\n" + 
			"  </url>\n" + 
			"</urlset>";
		String sitemap = writeSingleSiteMap(wsg);
		assertEquals(expected, sitemap);
	}
	
	public void testAll() throws Exception {
		W3CDateFormat dateFormat = new W3CDateFormat(Pattern.SECOND);
		dateFormat.setTimeZone(W3CDateFormat.ZULU);
		wsg = GoogleNewsSitemapGenerator.builder("http://www.example.com", dir)
			.dateFormat(dateFormat).build();
		GoogleNewsSitemapUrl url = new GoogleNewsSitemapUrl.Options("Crisis Averted", "http://www.example.com/index.html", new Publication("Fox News", Locale.US), new Date(0))
			.keywords("Klaatu", "Barrata", "Nicto")
			.access(NewsAccess.Subscription)
			.genres(NewsGenre.Blog, NewsGenre.PressRelease)
			.build();
		wsg.addUrl(url);
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
			"<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:news=\"http://www.google.com/schemas/sitemap-news/0.9\" >\n" + 
			"  <url>\n" + 
			"    <loc>http://www.example.com/index.html</loc>\n" + 
			"    <news:news>\n" + 
			"      <news:publication>\n" + 
			"        <news:name>Fox News</news:name>\n" +
			"        <news:language>en</news:language>\n" +
			"      </news:publication>\n" + 
			"      <news:access>Subscription</news:access>\n" +
			"      <news:genres>Blog, PressRelease</news:genres>\n" +
			"      <news:publication_date>1970-01-01T00:00:00Z</news:publication_date>\n" + 
			"      <news:title>Crisis Averted</news:title>\n" +
			"      <news:keywords>Klaatu, Barrata, Nicto</news:keywords>\n" +
			"    </news:news>\n" + 
			"  </url>\n" + 
			"</urlset>";
		String sitemap = writeSingleSiteMap(wsg);
		assertEquals(expected, sitemap);
	}
	
	private String writeSingleSiteMap(GoogleNewsSitemapGenerator wsg) {
		List<File> files = wsg.write();
		assertEquals("Too many files: " + files.toString(), 1, files.size());
		assertEquals("Sitemap misnamed", "sitemap.xml", files.get(0).getName());
		return TestUtil.slurpFileAndDelete(files.get(0));
	}
}
