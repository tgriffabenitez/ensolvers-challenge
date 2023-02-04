package ensolvers.challenge.tomasgriffa.repository;

import ensolvers.challenge.tomasgriffa.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByIsActive(boolean status);
}
