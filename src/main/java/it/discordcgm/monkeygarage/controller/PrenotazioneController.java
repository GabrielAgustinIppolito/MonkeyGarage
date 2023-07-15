package it.discordcgm.monkeygarage.controller;

import it.discordcgm.monkeygarage.model.payload.request.CostoTipoVeicoloRequest;
import it.discordcgm.monkeygarage.model.service.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prenotazione")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    @PostMapping("addCost")
    public ResponseEntity<?> addCost(@RequestBody CostoTipoVeicoloRequest request){
        return prenotazioneService.addCost(request);
    }

    @GetMapping("getCost/{tipoVeicolo}")
    public ResponseEntity<?> addCost(@PathVariable String tipoVeicolo){
        return prenotazioneService.getCost(tipoVeicolo);
    }

    @GetMapping("allCosts")
    public ResponseEntity<?> getAllCost(){
        return prenotazioneService.getAllCost();
    }

}
