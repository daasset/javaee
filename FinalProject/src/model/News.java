package model;

import java.time.LocalDateTime;

public class News {
    private Long id;
    private User user;
    private Category category;
    private LocalDateTime postedTime;
    private String title;
    private String content;

    public News() {
    }

    public News(Long id, User user, Category category, LocalDateTime postedTime, String title, String content) {
        this.id = id;
        this.user = user;
        this.category = category;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
