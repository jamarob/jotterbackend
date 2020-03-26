package com.jamarob.jotterbackend;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class Note implements Comparable<Note>{

    @Id
    private String id;
    private String text;
    private Instant created;
    private Instant edited;

    public Note(){}

    public String getId(){
        return this.id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getEdited() {
        return edited;
    }

    public void setEdited(Instant edited) {
        this.edited = edited;
    }

    @Override
    public int compareTo(Note other) {
        return other.created.compareTo(this.created);
    }
}
