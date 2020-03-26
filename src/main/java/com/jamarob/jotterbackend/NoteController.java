package com.jamarob.jotterbackend;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NoteController {

    private NoteRepository repository;

    public NoteController(NoteRepository repository){
        this.repository = repository;
    }

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @GetMapping("/notes")
    public List<NoteResponseDTO> getNotes(){
        return this.repository
                .findAll()
                .stream()
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
        this.repository.saveAll(notes);
        return notes.stream().map(NoteResponseDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/notes")
    public NoteResponseDTO createNote(@RequestBody @Valid NoteRequestDTO dto){
        Note note = new Note();
        note.setText(dto.getText());
        long now = System.currentTimeMillis();
        note.setCreated(now);
        note.setEdited(now);
        repository.save(note);
        return new NoteResponseDTO((note));
    }

    @GetMapping("/note/{id}")
    public NoteResponseDTO getNote(@PathVariable String id){
        Note note = this.repository.findById(id).orElseThrow(RuntimeException::new);
        return new NoteResponseDTO(note);
    }

    @PutMapping("/note/{id}")
    public NoteResponseDTO updateNote(@PathVariable String id, @RequestBody @Valid NoteRequestDTO dto){
        Note note = this.repository.findById(id).orElseThrow(RuntimeException::new);
        note.setText(dto.getText());
        note.setCreated((dto.getCreated()));
        note.setEdited(dto.getEdited());
        this.repository.save(note);
        return new NoteResponseDTO(note);
    }

    @DeleteMapping("/note/{id}")
    public NoteResponseDTO deleteNote(@PathVariable String id){
        Note note = this.repository.findById(id).orElseThrow(RuntimeException::new);
        this.repository.delete(note);
        return new NoteResponseDTO(note);
    }

}
