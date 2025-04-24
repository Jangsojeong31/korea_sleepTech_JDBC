package main.java.entity;

import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Post {
    private int id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    // 생성자 (Setter의 역할)
    public Post(int id, String title, String content, String author, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    // 생성자 (DB 결정값(auto_increment 등)을 제외한 필드 설정)
    public Post(String title, String content, String author) {
        // this: 해당 인스턴스 그 자체
        // this(): 해당 인스턴스의 생성자 호출
        this(0, title, content, author, LocalDateTime.now());
    }

    // Getter
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setter
    public void setTitle(String title) {this.title = title;}
    public void setContent(String content) {this.content = content;}


}
