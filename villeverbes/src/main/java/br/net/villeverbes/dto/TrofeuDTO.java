package br.net.villeverbes.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrofeuDTO {
    private String selo;      // Ex: "Maison", "Université", etc.
    private int value;        // Quantidade de selos
    private String medalha;   // 🥇, 🥈, 🥉 ou null
}
