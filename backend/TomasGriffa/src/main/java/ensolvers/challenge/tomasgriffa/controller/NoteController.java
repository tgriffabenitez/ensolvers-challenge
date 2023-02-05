package ensolvers.challenge.tomasgriffa.controller;

import ensolvers.challenge.tomasgriffa.dto.NoteDTO;
import ensolvers.challenge.tomasgriffa.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping(path = "/")
    public ResponseEntity<?> createNote(@RequestBody NoteDTO noteDTO, BindingResult bindingResult) {
        return noteService.createNote(noteDTO, bindingResult);
    }

    @PatchMapping(path = "/{noteId}")
    public ResponseEntity<?> modifyNote(@PathVariable Integer noteId, @RequestBody NoteDTO noteDTO, BindingResult bindingResult){
        return noteService.modifyNote(noteId, noteDTO, bindingResult);
    }

    @DeleteMapping(path = "/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Integer noteId) {
        return noteService.deleteNote(noteId);
    }

 }
