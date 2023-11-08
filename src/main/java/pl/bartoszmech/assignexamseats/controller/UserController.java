package pl.bartoszmech.assignexamseats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszmech.assignexamseats.model.dto.UserDto;
import pl.bartoszmech.assignexamseats.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @PatchMapping("/{userId}")
    public ResponseEntity<UserDto> update(@PathVariable long userId, @RequestBody UserDto inputUser) {
        return ResponseEntity.ok().body(service.edit(userId, inputUser));
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable long userId) {
        service.deleteById(userId);
    }
}
