package br.com.joaoramos.todolist.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatusCode.*;

@RestController
@RequestMapping("/created")
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;
    @PostMapping("/")
    public ResponseEntity createdUser(@RequestBody UserModel userModel){
        UserModel findUsername = iUserRepository.findByUsername(userModel.getUsername());
        if (findUsername != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        UserModel userCreated = iUserRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userCreated);

    }
}
