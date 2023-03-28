package model;

import java.time.LocalDateTime;

public class News {
    private Long id;
    private Long userId;
    private Long categoryId;
    private LocalDateTime postedTime;
    private String title;
    private String content;

    public News() {
    }

    public News(Long id, Long userId, Long categoryId, LocalDateTime postedTime, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.postedTime = postedTime;
        this.title = title;
        this.content = content;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(LocalDateTime postedTime) {
        this.postedTime = postedTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
