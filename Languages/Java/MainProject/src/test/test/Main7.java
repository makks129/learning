package test.test;

import java.util.ArrayList;
import java.util.List;

public class Main7 {

	private static List<NewsItem> newsList;

	public static void main(String[] args) {

		newsList = new ArrayList<NewsItem>();
		for (int i = 0; i < 10; i++) {
			NewsItem x = new NewsItem();
			x.setNewsId(i);
			newsList.add(x);
		}

		List<NewsItem> loadMoreList = new ArrayList<NewsItem>();
		for (int i = 9; i < 19; i++) {
			NewsItem x = new NewsItem();
			x.setNewsId(i);
			loadMoreList.add(x);
		}

		loadMoreList = removeDuplicates(loadMoreList);

		newsList.addAll(loadMoreList);

		System.out.println();

	}

	private static List<NewsItem> removeDuplicates(List<NewsItem> list) {
		List<NewsItem> noDuplicates = new ArrayList<NewsItem>();
		for (NewsItem item : list) {
			if (!newsList.contains(item)) {
				noDuplicates.add(item);
			}
		}
		return noDuplicates;
	}


}