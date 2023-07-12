package it.discordcgm.monkeygarage.model.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;

@Getter
@NonNull
public class RegistrazioneRequest {
    @NotBlank
    @Size(min=6, max=20)
    private String nome;
    @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$",
            message="La password può contenere solo caratteri maiuscoli e minuscoli e numeri. La lunghezza deve essere compresa tra 6 e 10 caratteri")
    private String password;
    @Email
    @NotBlank
    private String email;
}
