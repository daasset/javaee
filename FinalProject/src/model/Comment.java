package model;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Long userId;
    private Long newsId;
    private LocalDateTime postedTime;
    private String text;

    public Comment() {
    }

    public Comment(Long id, Long userId, Long newsId, LocalDateTime postedTime, String text) {
        this.id = id;
        this.userId = userId;
        this.newsId = newsId;
        this.postedTime = postedTime;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
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
