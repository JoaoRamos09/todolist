package br.com.joaoramos.todolist.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.CryptoPrimitive;

import static org.springframework.http.HttpStatusCode.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;
    @PostMapping("/")
    public ResponseEntity createdUser(@RequestBody UserModel userModel){
        UserModel findUsername = iUserRepository.findByUsername(userModel.getUsername());
        if (findUsername != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        String passwordCrypto = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordCrypto);

        UserModel userCreated = iUserRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);

    }
}
