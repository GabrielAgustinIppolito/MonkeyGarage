package it.discordcgm.monkeygarage.controller;

import it.discordcgm.monkeygarage.model.payload.request.RegistrazioneRequest;
import it.discordcgm.monkeygarage.model.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class UserController {

    private final UserService userService;

    @PostMapping("registrazione")
    public ResponseEntity<?> registrazione(@RequestBody @Valid RegistrazioneRequest request){
        return userService.registrazione(request);

    }




}


