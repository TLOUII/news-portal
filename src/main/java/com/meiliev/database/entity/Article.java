package com.meiliev.database.entity;

import java.io.Serializable;



public class Article implements Serializable {


    private Long id;
    private String title;
    private String content;


    public Article() {
    }

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Article{" + "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' + '}';
    }


}
