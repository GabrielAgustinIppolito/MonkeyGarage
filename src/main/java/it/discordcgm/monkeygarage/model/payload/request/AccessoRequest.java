package it.discordcgm.monkeygarage.model.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AccessoRequest {

    @NotBlank
    private String nomeOrEmail;
    @NotBlank
    private String password;


}
