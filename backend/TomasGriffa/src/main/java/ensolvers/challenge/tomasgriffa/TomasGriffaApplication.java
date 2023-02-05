package ensolvers.challenge.tomasgriffa;

import ensolvers.challenge.tomasgriffa.model.Note;
import ensolvers.challenge.tomasgriffa.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TomasGriffaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomasGriffaApplication.class, args);
    }

    @Autowired
    private NoteRepository noteRepository;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            Note note1 = noteRepository.save(new Note("titulo1", "cuerpo1"));
            Note note2 = noteRepository.save(new Note("titulo2", "cuerpo2"));
            Note note3 = noteRepository.save(new Note("titulo3", "cuerpo3"));
            Note note4 = noteRepository.save(new Note("titulo4", "cuerpo4"));

        };
    }
}
