package it.discordcgm.monkeygarage.model.service;

import it.discordcgm.monkeygarage.exception.ResourceNotFoundException;
import it.discordcgm.monkeygarage.model.entity.Ruolo;
import it.discordcgm.monkeygarage.model.entity.User;
import it.discordcgm.monkeygarage.model.payload.request.AccessoRequest;
import it.discordcgm.monkeygarage.model.payload.request.RegistrazioneRequest;
import it.discordcgm.monkeygarage.model.payload.response.JwtAuthenticationResponse;
import it.discordcgm.monkeygarage.model.repository.UserRepository;
import it.discordcgm.monkeygarage.security.JwtTokenProvider;
import it.discordcgm.monkeygarage.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RuoloService ruoloService;

    // cripta la password
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


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
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(u);
        return new ResponseEntity<>("nome: " + u.getUsername(), HttpStatus.OK);
    }

    public ResponseEntity<?> accesso(AccessoRequest request){
        User user = getUser(request.getNomeOrEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            return new ResponseEntity<>("Incorrect mail or password", HttpStatus.FORBIDDEN);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getNomeOrEmail(), request.getPassword()) //verifica se è un utente valido
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generare risposta jwt
        String jwt = JwtTokenProvider.generateToken(authentication);
        JwtAuthenticationResponse currentUser = UserPrincipal.createJwtAuthenticationResponseFromUserPrincipal((UserPrincipal) authentication.getPrincipal(), jwt);

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    private User getUser (String nomeOrEmail){
        return userRepository.findByUsernameOrEmail(nomeOrEmail, nomeOrEmail)
                .orElseThrow(() -> new ResourceNotFoundException("NomeOrEmail", "", null));
    }

}
