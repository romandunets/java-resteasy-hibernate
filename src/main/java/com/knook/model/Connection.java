package com.knook.model;

import java.io.Serializable;
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
@Table(name="connections")
public class Connection implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "note_a_id")
    private Note noteA;

    @ManyToOne
    @JoinColumn(name = "note_b_id")
    private Note noteB;

    @ManyToOne
    @JoinColumn(name = "connection_type_id")
    private ConnectionType connectionType;

    @Column(name="created_at", updatable = false, nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="updated_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Connection() {
        this.id = 0l;
        this.noteA = new Note();
        this.noteB = new Note();
        this.connectionType = new ConnectionType();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Connection(Note noteA, Note noteB, ConnectionType connectionType) {
        this.id = 0l;
        this.noteA = noteA;
        this.noteB = noteB;
        this.connectionType = connectionType;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Note getNoteA() {
        return noteA;
    }

    public void setNoteA(Note noteA) {
        this.noteA = noteA;
    }

    public Note getNoteB() {
        return noteB;
    }

    public void setNoteB(Note noteB) {
        this.noteB = noteB;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
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
