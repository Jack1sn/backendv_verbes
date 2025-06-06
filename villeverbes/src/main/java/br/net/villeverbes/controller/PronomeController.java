
package br.net.villeverbes.controller;

import br.net.villeverbes.dto.PronomeDTO;
import br.net.villeverbes.service.PronomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pronomes")
public class PronomeController {

    @Autowired
    private PronomeService pronomeService;

    @GetMapping
    public List<PronomeDTO> listarTodos() {
        return pronomeService.listarTodos();
    }

    @GetMapping("/{id}")
    public PronomeDTO buscarPorId(@PathVariable Long id) {
        return pronomeService.buscarPorId(id).orElseThrow();
    }

    @PostMapping
    public PronomeDTO criar(@RequestBody PronomeDTO dto) {
        return pronomeService.salvar(dto);
    }

    @PutMapping("/{id}")
    public PronomeDTO atualizar(@PathVariable Long id, @RequestBody PronomeDTO dto) {
        dto.setId(id);
        return pronomeService.salvar(dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pronomeService.deletar(id);
    }
}
