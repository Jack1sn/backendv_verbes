
package br.net.villeverbes.controller;

import br.net.villeverbes.dto.ComplementoDTO;
import br.net.villeverbes.service.ComplementoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/complementos")
public class ComplementoController {

    @Autowired
    private ComplementoService complementoService;

    @GetMapping
    public List<ComplementoDTO> listarTodos() {
        return complementoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ComplementoDTO buscarPorId(@PathVariable Long id) {
        return complementoService.buscarPorId(id).orElseThrow();
    }

    @PostMapping
    public ComplementoDTO criar(@RequestBody ComplementoDTO dto) {
        return complementoService.salvar(dto);
    }

    @PutMapping("/{id}")
    public ComplementoDTO atualizar(@PathVariable Long id, @RequestBody ComplementoDTO dto) {
        dto.setId(id);
        return complementoService.salvar(dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        complementoService.deletar(id);
    }
}
