package it.discordcgm.monkeygarage.model.service;

import it.discordcgm.monkeygarage.model.entity.CostoTipoVeicolo;
import it.discordcgm.monkeygarage.model.entity.TipoVeicolo;
import it.discordcgm.monkeygarage.model.payload.request.CostoTipoVeicoloRequest;
import it.discordcgm.monkeygarage.model.payload.response.CostoTipoVeicoloResponse;
import it.discordcgm.monkeygarage.model.repository.CostoTipoVeicoloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final CostoTipoVeicoloRepository costoTipoVeicoloRepository;

    public ResponseEntity<?> addCost(CostoTipoVeicoloRequest request){

        var s = Arrays.stream(TipoVeicolo.values()).anyMatch(
                v -> v.toString().equals(request.getTipoVeicolo()));
        if(!s){
            return new ResponseEntity<>("Non esiste il tipo di veicolo", HttpStatus.BAD_REQUEST);
        }

        Optional<CostoTipoVeicolo> c = costoTipoVeicoloRepository.findByTipoVeicolo(TipoVeicolo.valueOf(request.getTipoVeicolo()));

        if(!c.isEmpty()){
            c.get().setCostoPerGiorni(request.getCostoPerGiorni());
            c.get().setCostoPerOra(request.getCostoPerOra());
            costoTipoVeicoloRepository.save(c.get());
            return new ResponseEntity<>("Costi aggiornati con successo", HttpStatus.OK);
        }

        costoTipoVeicoloRepository.save(new CostoTipoVeicolo(request.getCostoPerOra(),
                request.getCostoPerGiorni(),
                TipoVeicolo.valueOf(request.getTipoVeicolo())));
        return new ResponseEntity<>("Costo tipo veicolo creato con successo!", HttpStatus.CREATED);

    }

    public ResponseEntity<?> getCost(String tV) {
        String tipoVeicolo = tV.toUpperCase().trim();

        var s = Arrays.stream(TipoVeicolo.values()).anyMatch(
                v -> v.toString().equals(tipoVeicolo));
        if(!s){
            return new ResponseEntity<>("Non esiste il tipo di veicolo", HttpStatus.BAD_REQUEST);
        }
        Optional<CostoTipoVeicoloResponse> costo = costoTipoVeicoloRepository.
                getCostoByTipoVeicolo(TipoVeicolo.valueOf(tipoVeicolo));
        if(costo.isEmpty())
            return new ResponseEntity<>("Non esiste un prezzo associato a questo tipo di veicolo", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(costo, HttpStatus.OK);
    }
}
