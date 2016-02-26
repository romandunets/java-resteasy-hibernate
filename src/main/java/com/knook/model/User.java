package com.knook.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="created_at", updatable = false, nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="updated_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private Set<Note> notes;
    
    public User() {
        this.id = 0l;
        this.email = "";
        this.password = "";
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.notes = new HashSet<>();
    }

    public User(Long id) {
        this.id = id;
        this.email = "";
        this.password = "";
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.notes = new HashSet<>();
    }

    public User(String email, String password) {
        this.id = 0l;
        this.email = email;
        this.password = password;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.notes = new HashSet<>();
    }

    public User(Long id, String email, String password, Date createdAt, Date updatedAt, Set<Note> notes) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.notes = notes;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
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
