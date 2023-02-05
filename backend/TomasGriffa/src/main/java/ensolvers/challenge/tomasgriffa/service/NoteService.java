package ensolvers.challenge.tomasgriffa.service;

import ensolvers.challenge.tomasgriffa.dto.NoteDTO;
import ensolvers.challenge.tomasgriffa.model.Note;
import ensolvers.challenge.tomasgriffa.model.User;
import ensolvers.challenge.tomasgriffa.repository.NoteRepository;
import ensolvers.challenge.tomasgriffa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createNote(NoteDTO noteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (noteDTO.getTitle().isEmpty() || noteDTO.getNote().isEmpty() || noteDTO.getUserId() == null)
            return new ResponseEntity<>("Must provide a title and a note", HttpStatus.BAD_REQUEST);

        User user = userRepository.findById(noteDTO.getUserId()).orElse(null);
        if (user == null)
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

        if (noteDTO.getTitle().isEmpty() || noteDTO.getNote().isEmpty() || noteDTO.getUserId() == null)
            return new ResponseEntity<>("Must providea title and a note", HttpStatus.BAD_REQUEST);

        Note note = noteRepository.save(new Note(noteDTO.getTitle(), noteDTO.getNote()));

        user.addNote(note);
        userRepository.save(user);

        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    public ResponseEntity<?> modifyNote(Integer noteId, NoteDTO noteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        Note note = noteRepository.findById(noteId).orElse(null);
        if (note == null)
            return new ResponseEntity<>("Note not found", HttpStatus.NOT_FOUND);

        if (!noteDTO.getTitle().isEmpty())
            note.setTitle(noteDTO.getTitle());

        if (!noteDTO.getNote().isEmpty())
            note.setNote(noteDTO.getNote());

        if (noteDTO.getIsActive().equals(true))
            note.setIsActive(true);

        if (noteDTO.getIsActive().equals(false))
            note.setIsActive(false);

        note.setModified(LocalDateTime.now());
        noteRepository.save(note);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }


    public ResponseEntity<?> deleteNote(Integer noteId) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note == null)
            return new ResponseEntity<>("Note not found", HttpStatus.NOT_FOUND);

        noteRepository.delete(note);
        return new ResponseEntity<>("Note deleted", HttpStatus.OK);
    }

}
