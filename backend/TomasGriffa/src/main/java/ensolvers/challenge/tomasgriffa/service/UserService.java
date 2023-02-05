package ensolvers.challenge.tomasgriffa.service;

import ensolvers.challenge.tomasgriffa.dto.UserDTO;
import ensolvers.challenge.tomasgriffa.model.User;
import ensolvers.challenge.tomasgriffa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    public ResponseEntity<?> login(UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        User user = userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if (user == null)
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    public ResponseEntity<?> getUserActiveNotes(Integer userId) {
        if (!userRepository.existsById(userId)) return new ResponseEntity<>("nop", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(
                em.createQuery("SELECT n FROM User u JOIN u.notes n WHERE u.id = :userId AND n.isActive = true")
                        .setParameter("userId", userId)
                        .getResultList(),
                HttpStatus.OK
        );

    }

    public ResponseEntity<?> getUserArchivedNotes(Integer userId) {
        if (!userRepository.existsById(userId)) return new ResponseEntity<>("nop", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(
                em.createQuery("SELECT n FROM User u JOIN u.notes n WHERE u.id = :userId AND n.isActive = false")
                        .setParameter("userId", userId)
                        .getResultList(),
                HttpStatus.OK
        );

    }
}
