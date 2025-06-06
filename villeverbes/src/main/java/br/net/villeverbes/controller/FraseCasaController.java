package br.net.villeverbes.controller;

import br.net.villeverbes.dto.FraseCasaDTO;
import br.net.villeverbes.service.FraseCasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/frases-casa")
public class FraseCasaController {

    @Autowired
    private FraseCasaService fraseCasaService;

    // GET todas as frases
    @GetMapping
    public List<FraseCasaDTO> listarTodas() {
        return fraseCasaService.listarTodas();
    }

    // GET por ID
    @GetMapping("/{id}")
    public FraseCasaDTO buscarPorId(@PathVariable Long id) {
        return fraseCasaService.buscarPorId(id).orElseThrow();
    }

    // POST - criar nova
    @PostMapping
    public FraseCasaDTO criar(@RequestBody FraseCasaDTO dto) {
        return fraseCasaService.salvar(dto);
    }

    // PUT - atualizar existente
    @PutMapping("/{id}")
    public FraseCasaDTO atualizar(@PathVariable Long id, @RequestBody FraseCasaDTO dto) {
        dto.setId(id); // Garante que está atualizando o correto
        return fraseCasaService.salvar(dto); // Reutiliza o método salvar
    }

    // DELETE - remover por ID
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        fraseCasaService.deletar(id);
    }
}
