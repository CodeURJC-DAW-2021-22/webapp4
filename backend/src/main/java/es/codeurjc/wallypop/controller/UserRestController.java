package es.codeurjc.wallypop.controller;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    List<User> all() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {

        Optional<User> op = userService.findById(id);
        if (op.isPresent()) {
            User user = op.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        user.setPASSWORD(userService.encodePassword(user.getPASSWORD()));
        userService.save(user);
        return user;
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updatedUser) throws SQLException {
        if (userService.exist(id)) {
            updatedUser.setID_USER(id);
            userService.save(updatedUser);

            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {

        try {
            userService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
