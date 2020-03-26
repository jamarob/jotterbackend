package com.jamarob.jotterbackend;

import java.time.Instant;

public class NoteResponseDTO {

    private String id;
    private String text;
    private Instant created;
    private Instant edited;

    public NoteResponseDTO(Note note){
        this.id = note.getId();
        this.text = note.getText();
        this.created = note.getCreated();
        this.edited = note.getEdited();
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Instant getCreated() {
        return created;
    }

    public Instant getEdited() {
        return edited;
    }
}
