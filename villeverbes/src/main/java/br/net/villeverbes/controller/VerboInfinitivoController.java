package br.net.villeverbes.controller;

import br.net.villeverbes.dto.VerboInfinitivoDTO;
import br.net.villeverbes.service.VerboInfinitivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/verbos")
public class VerboInfinitivoController {

    @Autowired
    private VerboInfinitivoService verboInfinitivoService;

    @GetMapping
    public List<VerboInfinitivoDTO> listarTodos() {
        return verboInfinitivoService.listarTodos();
    }

    @GetMapping("/{id}")
    public VerboInfinitivoDTO buscarPorId(@PathVariable Long id) {
        return verboInfinitivoService.buscarPorId(id).orElseThrow();
    }

    @PostMapping
    public VerboInfinitivoDTO criar(@RequestBody VerboInfinitivoDTO dto) {
        return verboInfinitivoService.salvar(dto);
    }

    @PutMapping("/{id}")
    public VerboInfinitivoDTO atualizar(@PathVariable Long id, @RequestBody VerboInfinitivoDTO dto) {
        dto.setId(id);
        return verboInfinitivoService.salvar(dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        verboInfinitivoService.deletar(id);
    }
}
