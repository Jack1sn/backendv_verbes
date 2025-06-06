package br.net.villeverbes.controller;


import br.net.villeverbes.dto.TempoVerbalDTO;
import br.net.villeverbes.service.TempoVerbalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tempos")
public class TempoVerbalController {

    @Autowired
    private TempoVerbalService tempoVerbalService;

    @GetMapping
    public List<TempoVerbalDTO> listarTodos() {
        return tempoVerbalService.listarTodos();
    }

    @GetMapping("/{id}")
    public TempoVerbalDTO buscarPorId(@PathVariable Long id) {
        return tempoVerbalService.buscarPorId(id).orElseThrow();
    }

    @PostMapping
    public TempoVerbalDTO criar(@RequestBody TempoVerbalDTO dto) {
        return tempoVerbalService.salvar(dto);
    }

    @PutMapping("/{id}")
    public TempoVerbalDTO atualizar(@PathVariable Long id, @RequestBody TempoVerbalDTO dto) {
        dto.setId(id);
        return tempoVerbalService.salvar(dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        tempoVerbalService.deletar(id);
    }
}
