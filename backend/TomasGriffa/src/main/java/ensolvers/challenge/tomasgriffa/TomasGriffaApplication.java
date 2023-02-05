package ensolvers.challenge.tomasgriffa;

import ensolvers.challenge.tomasgriffa.model.Note;
import ensolvers.challenge.tomasgriffa.model.User;
import ensolvers.challenge.tomasgriffa.repository.NoteRepository;
import ensolvers.challenge.tomasgriffa.repository.UserRepository;
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
//
//    @Autowired
//    private NoteRepository noteRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//    @Bean
//    public CommandLineRunner init() {
//        return args -> {
//
//            Note note1 = noteRepository.save(new Note("Titulo 1", "nota 1"));
//            Note note2 = noteRepository.save(new Note("Titulo 2", "nota 2"));
//
//            User user = new User("admin@gmail.com", "1234");
//            user.addNote(note1);
//            user.addNote(note2);
//            userRepository.save(user);
//
//        };
//    }
}
