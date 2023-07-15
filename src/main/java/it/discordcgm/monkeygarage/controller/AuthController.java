package it.discordcgm.monkeygarage.controller;

import it.discordcgm.monkeygarage.model.payload.request.RegistrazioneRequest;
import it.discordcgm.monkeygarage.model.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("registrazione")
    public ResponseEntity<?> registrazione(@RequestBody @Valid RegistrazioneRequest request){
        return authService.registrazione(request);

    }

}
