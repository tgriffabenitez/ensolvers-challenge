package ensolvers.challenge.tomasgriffa.service;

import ensolvers.challenge.tomasgriffa.dto.NoteDTO;
import ensolvers.challenge.tomasgriffa.model.Note;
import ensolvers.challenge.tomasgriffa.repository.NoteRepository;
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

    /**
     * This class is a Java method that implements a REST endpoint for creating a note.
     * It injects the {@link NoteRepository} to interact with the database.
     *
     * @param noteDTO a data transfer object that contains the title and note information
     * @param bindingResult an object that holds the validation results
     *
     * @return A response entity that indicates the result of the note creation process.
     * If there are any validation errors, the response will have a `BAD_REQUEST` status and
     * the errors will be included in the response body. If the title and note fields are empty,
     * a `BAD_REQUEST` status with an error message will be returned. If the note is created
     * successfully, the response will have an `OK` status and the created note will be included
     * in the response body.
     */
    public ResponseEntity<?> createNote(NoteDTO noteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (noteDTO.getTitle().isEmpty() || noteDTO.getNote().isEmpty())
            return new ResponseEntity<>("Must providea title and a note", HttpStatus.BAD_REQUEST);

        Note note = new Note(noteDTO.getTitle(), noteDTO.getNote());
        noteRepository.save(note);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * This class is a Java method that implements a REST endpoint for modifying a note.
     * It uses the {@link NoteRepository} to interact with the database.
     *
     * @param noteId the ID of the note to be modified
     * @param noteDTO a data transfer object that contains the new title and note information
     * @param bindingResult an object that holds the validation results
     *
     * @return A response entity that indicates the result of the note modification process.
     * If there are any validation errors, the response will have a `BAD_REQUEST` status and
     * the errors will be included in the response body. If the note with the specified ID
     * is not found, the response will have a `NOT_FOUND` status with an error message.
     * If the modification is successful, the response will have an `OK` status and the
     * modified note will be included in the response body.
     */
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


    /**
     * This class is a Java method that implements a REST endpoint for deleting a note.
     * It uses the {@link NoteRepository} to interact with the database.
     *
     * @param noteId the ID of the note to be deleted
     *
     * @return A response entity that indicates the result of the note deletion process.
     * If the note with the specified ID is not found, the response will have a `NOT_FOUND`
     * status with an error message. If the deletion is successful, the response will have
     * an `OK` status and a success message.
     */
    public ResponseEntity<?> deleteNote(Integer noteId) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note == null)
            return new ResponseEntity<>("Note not found", HttpStatus.NOT_FOUND);

        noteRepository.delete(note);
        return new ResponseEntity<>("Note deleted", HttpStatus.OK);
    }

    /**
     * This method is a Java method that implements a REST endpoint for retrieving all active notes.
     * It uses the {@link NoteRepository} to interact with the database.
     *
     * @return A response entity that contains all the active notes. The response has an `OK` status.
     */
    public ResponseEntity<?> listActiveNotes() {
        return new ResponseEntity<>(noteRepository.findByIsActive(true), HttpStatus.OK);
    }

    /**
     * This method is a Java method that implements a REST endpoint for retrieving all active notes.
     * It uses the {@link NoteRepository} to interact with the database.
     *
     * @return A response entity that contains all the archived notes. The response has an `OK` status.
     */
    public ResponseEntity<?> listArchivedNotes() {
        return new ResponseEntity<>(noteRepository.findByIsActive(false), HttpStatus.OK);
    }
}
