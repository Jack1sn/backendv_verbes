package br.net.villeverbes.controller;

import br.net.villeverbes.dto.SeloMedalhaDTO;
import br.net.villeverbes.service.SeloMedalhaService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/selos")
public class SeloMedalhaController {

    @Autowired
    private SeloMedalhaService seloMedalhaService;

    // Endpoint para retornar todos os selos de um jogador
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<SeloMedalhaDTO>> getSelosByUsuario(@PathVariable Long usuarioId) {
        List<SeloMedalhaDTO> selos = seloMedalhaService.getSelosByUsuarioId(usuarioId);

        // Se não houver selos encontrados, retorna um código de status 404 (não encontrado)
        if (selos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(selos);  // Retorna os selos com sucesso (status 200)
    }
}
