package es.codeurjc.wallypop.controller.api;

import es.codeurjc.wallypop.model.User;
import es.codeurjc.wallypop.dto.UserDTO;
import es.codeurjc.wallypop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDTO newAccount) {
        newAccount.setPASSWORD(userService.encode(newAccount.getPASSWORD()));
        User user = new User();
        user.setPASSWORD(newAccount.getPASSWORD());
        user.setFULL_NAME(newAccount.getFULL_NAME());
        user.setNAME(newAccount.getNAME());
        user.setTEL(newAccount.getTEL());
        userService.save(user);
        return user;
    }

    /*@GetMapping("")
    public ResponseEntity<User> mew(HttpServletRequest request) {
        return me(request);
    } */

    @GetMapping("")
    public ResponseEntity<User> me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            return ResponseEntity.ok(userService.findByNAME(principal.getName()).orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser(HttpServletRequest request, @RequestBody User updatedUser) throws SQLException {
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            return userService.updateUser(userService.findByNAME(principal.getName()).get().getID_USER(), updatedUser);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("")
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
