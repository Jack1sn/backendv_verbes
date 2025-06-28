package br.net.villeverbes.controller;

import br.net.villeverbes.dto.SeloMedalhaDTO;
import br.net.villeverbes.service.SeloMedalhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/selo-medalha")
public class SeloMedalhaController {

    @Autowired
    private SeloMedalhaService seloMedalhaService;

    // Endpoint para buscar SeloMedalha por usuarioId
    @GetMapping("/usuario/{usuarioId}")
    public SeloMedalhaDTO getSeloMedalhaByUsuarioId(@PathVariable Long usuarioId) {
        return seloMedalhaService.getSeloMedalhaByUsuarioId(usuarioId);
    }
}
