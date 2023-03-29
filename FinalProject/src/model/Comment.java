package model;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private User user;
    private News news;
    private LocalDateTime postedTime;
    private String text;

    public Comment() {
    }

    public Comment(Long id, User user, News news, LocalDateTime postedTime, String text) {
        this.id = id;
        this.user = user;
        this.news = news;
        this.postedTime = postedTime;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public LocalDateTime getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(LocalDateTime postedTime) {
        this.postedTime = postedTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
