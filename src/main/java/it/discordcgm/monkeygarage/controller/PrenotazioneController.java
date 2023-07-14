package it.discordcgm.monkeygarage.controller;

import it.discordcgm.monkeygarage.model.payload.request.CostoTipoVeicoloRequest;
import it.discordcgm.monkeygarage.model.service.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prenotazione")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    @PostMapping("addCost")
    public ResponseEntity<?> addCost(@RequestBody CostoTipoVeicoloRequest request){
        return prenotazioneService.addCost(request);
    }

}
