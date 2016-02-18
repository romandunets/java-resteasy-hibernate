package com.knook.model;

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

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private Set<Note> notes;
    
    public User() {
        this.id = 0l;
        this.email = "";
        this.password = "";
        this.notes = new HashSet<>();
    }

    public User(String email, String password) {
        this.id = 0l;
        this.email = email;
        this.password = password;
        this.notes = new HashSet<>();
    }

    public User(Long id, String email, String password, Set<Note> notes) {
        this.id = id;
        this.email = email;
        this.password = password;
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

}
