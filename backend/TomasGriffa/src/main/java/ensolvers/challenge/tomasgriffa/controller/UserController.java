package ensolvers.challenge.tomasgriffa.controller;

import ensolvers.challenge.tomasgriffa.dto.UserDTO;
import ensolvers.challenge.tomasgriffa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "https://ensolvers-challenge-tmgb.web.app/")
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody  UserDTO userDTO, BindingResult bindingResult) {
        return userService.login(userDTO, bindingResult);
    }

    @CrossOrigin(origins = "https://ensolvers-challenge-tmgb.web.app/")
    @GetMapping(path = "/{userId}/active")
    public ResponseEntity<?> listActiveNotes(@PathVariable Integer userId) {
        return userService.getUserActiveNotes(userId);
    }

    @CrossOrigin(origins = "https://ensolvers-challenge-tmgb.web.app/")
    @GetMapping(path = "/{userId}/archived")
    public ResponseEntity<?> listArchivedNotes(@PathVariable Integer userId) {
        return userService.getUserArchivedNotes(userId);
    }

}
