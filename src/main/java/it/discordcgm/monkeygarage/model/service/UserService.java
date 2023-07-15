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


}
