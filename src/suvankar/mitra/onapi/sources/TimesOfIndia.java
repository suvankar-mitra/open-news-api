package suvankar.mitra.onapi.sources;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import suvankar.mitra.onapi.models.NewsElement;

public class TimesOfIndia {
	
	public static ArrayList<NewsElement> getLatestStories() throws IOException {
		ArrayList<NewsElement> newsElements = new ArrayList<>();
		Document doc = Jsoup.connect("http://timesofindia.indiatimes.com/").get();
		Elements elements = doc.select(".list9").get(0).getElementsByTag("ul").get(0).getElementsByTag("li");
		for(Element e:elements){
			String url = e.children().get(0).attr("href");
			if(url.isEmpty()) continue;
			url = "http://timesofindia.indiatimes.com"+url;
			newsElements.add(new NewsElement(url,e.text()));
		}
		newsElements = collectDataFromNews(newsElements);
		return newsElements;
	}
	
	public static ArrayList<NewsElement> getTopStories() throws IOException {
		ArrayList<NewsElement> newsElements = new ArrayList<>();
		Document doc = Jsoup.connect("http://timesofindia.indiatimes.com/").get();
		Elements elements = doc.select(".top-story").get(0).getElementsByTag("ul").get(0).getElementsByTag("li");
		//urls of stories
		for(Element e:elements){
			String url = e.children().get(0).attr("href");
			if(url.isEmpty()) continue;
			url = "http://timesofindia.indiatimes.com"+url;
			newsElements.add(new NewsElement(url,e.text()));
		}
		newsElements = collectDataFromNews(newsElements);
		return newsElements;
	}
	
	private static ArrayList<NewsElement> collectDataFromNews(ArrayList<NewsElement> newsElements) throws IOException {
		Document doc;
		for(NewsElement ne : newsElements) {
			if(ne.getUrl().isEmpty() || ne.getUrl()==null){
				continue;
			}
			if(ne.getUrl().contains("timesofindia.indiatimes.com/videos/") ||
					ne.getUrl().contains("beautypageants.indiatimes.com") ||
					ne.getUrl().contains("www.gadgetsnow.com")) {
				continue;
			}
			//title
			try {
				doc = Jsoup.connect(ne.getUrl()).get();
				Element e = doc.select(".heading1").get(0).getElementsByTag("h1").get(0);
				String title = e.text();
				ne.setTitle(title);
				//author
				try {
					e = doc.select(".auth_detail").get(0).getElementsByTag("span").get(0);
					String author = e.text();
					ne.setAuthor(author);
				} catch(Exception exp){}
				//time and date
				try {
					e = doc.select(".time_cptn").get(0).getElementsByTag("span").get(0);
					String time = e.text();
					ne.setTimePublished(time);
				} catch(Exception exp){}
				//image
				try {
					e = doc.select(".highlight.clearfix").get(0).getElementsByTag("img").get(0);
					String imgUrl = e.attr("src");
					ne.setImgUrl(imgUrl);
				} catch(Exception exp){}
				//article
				e = doc.select(".Normal").get(0).getElementsByTag("div").get(0);
				String article = e.text();
				int i = article.lastIndexOf('.');
				String last = article.substring(i+1).trim();
				if(last.startsWith("Read this story in")) {	//strip off the last line if it is not relevant
					article = article.substring(0, i+1);
				}
				ne.setArticle(article);
			} catch (Exception e) {}
		}
		return newsElements;
	}
}
