package test.test;

public class NewsItem {

	private int newsId;
	private int categoryId;
	private String title;
	private int commentsCount;
	private String imageUrl;
	private String author;
	private String previewText;
	private String fullText;
	private String newsLink;
	private long dateTimeMillis;


	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPreviewText() {
		return previewText;
	}

	public void setPreviewText(String previewText) {
		this.previewText = previewText;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public long getDateTimeMillis() {
		return dateTimeMillis;
	}

	public void setDateTimeMillis(long dateTimeMillis) {
		this.dateTimeMillis = dateTimeMillis;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getNewsLink() {
		return newsLink;
	}

	public void setNewsLink(String newsLink) {
		this.newsLink = newsLink;
	}

	@Override
	public boolean equals(Object o) {
		return o != null && o instanceof NewsItem && newsId == ((NewsItem) o).getNewsId();
	}


}
