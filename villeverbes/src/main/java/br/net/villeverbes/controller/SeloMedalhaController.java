package br.net.villeverbes.controller;

import br.net.villeverbes.dto.SeloMedalhaDTO;
import br.net.villeverbes.dto.TrofeuDTO;
import br.net.villeverbes.service.SeloMedalhaService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/selos")
public class SeloMedalhaController {

    @Autowired
    private SeloMedalhaService seloMedalhaService;

    // ✅ Retorna todos os selos de um único usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<SeloMedalhaDTO>> getSelosByUsuario(@PathVariable Long usuarioId) {
        List<SeloMedalhaDTO> selos = seloMedalhaService.getSelosByUsuarioId(usuarioId);
        if (selos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(selos);
    }

    // ✅ Retorna a posição/ranking do usuário
    @GetMapping("/usuario/{usuarioId}/ranking")
    public ResponseEntity<List<SeloMedalhaDTO>> getRankingDoUsuario(@PathVariable Long usuarioId) {
        List<SeloMedalhaDTO> rankingCompleto = seloMedalhaService.getRankingCompleto();
        List<SeloMedalhaDTO> selosDoUsuario = rankingCompleto.stream()
            .filter(selo -> selo.getUsuarioJogoId().equals(usuarioId))
            .collect(Collectors.toList());

        if (selosDoUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(selosDoUsuario);
    }

    // ✅ Ranking geral de todos os jogadores (público)
    @GetMapping("/ranking")
    public ResponseEntity<List<SeloMedalhaDTO>> getRankingGeral() {
        List<SeloMedalhaDTO> rankingCompleto = seloMedalhaService.getRankingCompleto();
        if (rankingCompleto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(rankingCompleto);
    }

    // ✅ NOVO: Admin pode visualizar todos os selos de todos os jogadores
   
    @GetMapping("/todos")
    public ResponseEntity<List<SeloMedalhaDTO>> getTodosSelosParaAdmin() {
        List<SeloMedalhaDTO> todosSelos = seloMedalhaService.getRankingCompleto(); // ou outro método se necessário

        if (todosSelos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.ok(todosSelos);
    }


    // ✅ Retorna todos os troféus de um usuário (selos + quantidade + medalha)
@GetMapping("/todos-simples")
public ResponseEntity<List<TrofeuDTO>> getTodosTrofeusDoUsuario(@PathVariable Long usuarioId) {
    List<TrofeuDTO> trofeus = seloMedalhaService.getTrofeusDoUsuario(usuarioId);

    if (trofeus.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    return ResponseEntity.ok(trofeus);
}

}
