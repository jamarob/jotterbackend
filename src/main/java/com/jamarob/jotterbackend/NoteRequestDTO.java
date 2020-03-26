package com.jamarob.jotterbackend;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

public class NoteRequestDTO {

    @NotBlank
    private String text;

    private Instant created;

    private Instant edited;

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
}
