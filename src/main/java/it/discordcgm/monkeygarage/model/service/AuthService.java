package it.discordcgm.monkeygarage.model.service;

import it.discordcgm.monkeygarage.model.entity.Ruolo;
import it.discordcgm.monkeygarage.model.entity.User;
import it.discordcgm.monkeygarage.model.payload.request.RegistrazioneRequest;
import it.discordcgm.monkeygarage.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RuoloService ruoloService;

    protected boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public ResponseEntity<?> registrazione(RegistrazioneRequest request){
        if(existsByEmail(request.getEmail()))
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);

        User u = request.toEntity(true);
        Optional<Ruolo> a = ruoloService.findByAuthorityName("RUOLO_UTENTE");
        if (!a.isPresent())
            return new ResponseEntity<>("Something went wrong during registration",
                    HttpStatus.UNPROCESSABLE_ENTITY);

        u.getRuoli().add(a.get());
        userRepository.save(u);
        return new ResponseEntity<>("nome: " + u.getNome(), HttpStatus.OK);
    }

}
