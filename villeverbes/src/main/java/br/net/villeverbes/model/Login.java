package br.net.villeverbes.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @Setter @Getter
    private String email;
    
    @Setter @Getter
    private String senha;
}

