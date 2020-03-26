package com.jamarob.jotterbackend;

public class NoteResponseDTO {

    private String id;
    private String text;
    private long created;
    private long edited;

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

    public long getCreated() {
        return created;
    }

    public long getEdited() {
        return edited;
    }
}
