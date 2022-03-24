package es.codeurjc.wallypop.controller;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        user.setPASSWORD(userService.encodePassword(user.getPASSWORD()));
        userService.save(user);
        return user;
    }

    @GetMapping("")
    public ResponseEntity<User> mew(HttpServletRequest request) {
        return me(request);
    }

    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            return ResponseEntity.ok(userService.findByNAME(principal.getName()).orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/me")
    public ResponseEntity<User> updateUser(HttpServletRequest request, @RequestBody User updatedUser) throws SQLException {
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            return userService.updateUser(userService.findByNAME(principal.getName()).get().getID_USER(), updatedUser);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/me")
    public ResponseEntity<User> deleteUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            try {
                userService.deleteById(userService.findByNAME(principal.getName()).get().getID_USER());
                return new ResponseEntity<>(null, HttpStatus.OK);
            } catch (EmptyResultDataAccessException e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
