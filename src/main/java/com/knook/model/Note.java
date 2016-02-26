package com.knook.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="notes")
public class Note {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="content", nullable=false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="created_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="updated_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    public Note() {
        this.id = 0l;
        this.title = "";
        this.content = "";
        this.user = new User();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Note(String title, String content) {
        this.id = 0l;
        this.title = title;
        this.content = content;
        this.user = new User();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Note(Long id, String title, String content, User user, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


}
