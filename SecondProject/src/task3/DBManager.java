package task3;

import java.util.ArrayList;

public class DBManager {
    private static final ArrayList<News> newsList = new ArrayList<>();
    private static long count = 0;

    public static void addNews(News news) {
        news.setId(count++);
        newsList.add(news);
    }

    public static ArrayList<News> getNewsList() {
        return newsList;
    }
}
