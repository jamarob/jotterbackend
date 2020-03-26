package com.jamarob.jotterbackend;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {

    private NoteRepository repository;

    public NoteController(NoteRepository repository){
        this.repository = repository;
    }

    @GetMapping("/notes")
    public List<NoteResponseDTO> getNotes(){
        return this.repository
                .findAll().stream()
                .sorted()
                .map(NoteResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PutMapping("/notes")
    public List<NoteResponseDTO> putNotes(@RequestBody @Valid List<NoteRequestDTO> dto){
        this.repository.deleteAll();
        List<Note> notes = dto.stream().map(ndto -> {
            Note note = new Note();
            note.setText(ndto.getText());
            note.setCreated(ndto.getCreated());
            note.setEdited(ndto.getEdited());
            return note;
        }).collect(Collectors.toList());
        List<Note> savedNotes = this.repository.saveAll(notes);
        return savedNotes.stream()
                .sorted()
                .map(NoteResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/notes")
    public NoteResponseDTO createNote(@RequestBody @Valid NoteRequestDTO dto){
        Note note = new Note();
        note.setText(dto.getText());
        Instant now = Instant.now();
        note.setCreated(now);
        note.setEdited(now);
        Note savedNote = this.repository.save(note);
        return new NoteResponseDTO(savedNote);
    }

    @GetMapping("/note/{id}")
    public NoteResponseDTO getNote(@PathVariable String id){
        Note note = this.repository.findById(id).orElseThrow(NoteNotFoundException::new);
        return new NoteResponseDTO(note);
    }

    @PutMapping("/note/{id}")
    public NoteResponseDTO updateNoteText(@PathVariable String id, @RequestBody @Valid NoteRequestDTO dto){
        Note note = this.repository.findById(id).orElseThrow(NoteNotFoundException::new);
        note.setText(dto.getText());
        note.setEdited(Instant.now());
        Note updatedNote = this.repository.save(note);
        return new NoteResponseDTO(updatedNote);
    }

    @DeleteMapping("/note/{id}")
    public void deleteNote(@PathVariable String id){
        Note note = this.repository.findById(id).orElseThrow(NoteNotFoundException::new);
        this.repository.delete(note);
    }

}
