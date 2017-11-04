package suvankar.mitra.onapi.models;

public class NewsElement {
	private String url;
	private String shortDesc;
	private String imgUrl;
	private String article;
	private String title;
	private String author;
	private String timePublished;
	public NewsElement(String description,String url, String imgUrl) {
		super();
		this.url = url;
		this.shortDesc = description;
		this.imgUrl = imgUrl;
	}
	
	public NewsElement(String url, String description) {
		super();
		this.url = url;
		this.shortDesc = description;
	}

	public NewsElement(){}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTimePublished() {
		return timePublished;
	}

	public void setTimePublished(String timePublished) {
		this.timePublished = timePublished;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+url+"]"+"["+imgUrl+"]"+"\n"+title+"\n"+(author==null ? "" : author+"|")+timePublished+"\n"+article+"\n------------\n";
	}
}
