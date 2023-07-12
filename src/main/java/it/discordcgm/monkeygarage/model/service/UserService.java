package it.discordcgm.monkeygarage.model.service;


import it.discordcgm.monkeygarage.model.entity.User;
import it.discordcgm.monkeygarage.model.payload.request.RegistrazioneRequest;
import it.discordcgm.monkeygarage.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    protected boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }



    public ResponseEntity<?> registrazione(RegistrazioneRequest request){
        if(existsByEmail(request.getEmail()))
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);
        User u = userRepository.save(new User(request.getNome(), request.getEmail(), request.getPassword(), true));
        return new ResponseEntity<>("nome:" + u.getNome(), HttpStatus.OK);
    }


}
