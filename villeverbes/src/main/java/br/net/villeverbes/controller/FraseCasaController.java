package br.net.villeverbes.controller;

import br.net.villeverbes.dto.FraseCasaDTO;
import br.net.villeverbes.service.FraseCasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/frases")
@Validated
public class FraseCasaController {

    @Autowired
    private FraseCasaService fraseCasaService;

    // GET todas as frases
    @GetMapping
    public List<FraseCasaDTO> listarTodas() {
        return fraseCasaService.listarTodas();
    }

    // GET por ID com tratamento de erro 404
    @GetMapping("/{id}")
    public FraseCasaDTO buscarPorId(@PathVariable Long id) {
        return fraseCasaService.buscarPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Frase não encontrada com id " + id));
    }

    // POST - criar nova com validação
   
    @PostMapping
    public ResponseEntity<FraseCasaDTO> salvarFrase(@RequestBody FraseCasaDTO dto) {
        // Salva a frase e retorna o DTO da resposta
        FraseCasaDTO savedFrase = fraseCasaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFrase);
    }

    // PUT - atualizar existente com validação e garantia do id
    @PutMapping("/{id}")
    public FraseCasaDTO atualizar(@PathVariable Long id, @Valid @RequestBody FraseCasaDTO dto) {
        dto.setId(id); // Garante que está atualizando o correto
        return fraseCasaService.salvar(dto);
    }

    // DELETE - remover por ID, retornando 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        fraseCasaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
