package br.net.villeverbes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.net.villeverbes.model.Verbo;

@RestController
@CrossOrigin


public class VerboController {

    @Autowired
    private br.net.villeverbes.repository.VerboRepository verboRepository;


    // Endpoint para obter todos os verbos
    @GetMapping("/verbos/all")
    public ResponseEntity<List<Verbo>> getVerbos() {
        List<Verbo> dados = verboRepository.findAll();
        return ResponseEntity.ok(dados);
    }

    // Endpoint para obter um verbo por ID
    @GetMapping("verbos/{id}")
    public ResponseEntity<Verbo> getVerboById(@PathVariable Integer id) {
        Verbo dados = verboRepository.findById(id).orElse(null);
        if (dados != null) {
            return ResponseEntity.ok(dados);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint para criar um novo verbo
    @PostMapping("/verbos/post")
    public ResponseEntity<Verbo> createVerbo(@RequestBody Verbo verbo) {
        Verbo dados = verboRepository.save(verbo);
        return ResponseEntity.ok(dados);
    }

    // Endpoint para atualizar um verbo existente
    @PutMapping("/verbos/put/{id}")
    public ResponseEntity<Verbo> updateVerbo(@PathVariable int id, @RequestBody Verbo verbo) {
        Verbo dados = verboRepository.findById(id).orElse(null);
        if (dados != null) {
            dados.setVerbo(verbo.getVerbo());
            verboRepository.save(dados);
            return ResponseEntity.ok(dados);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/verbos/delete/{id}")
    public ResponseEntity<Void> deleteVerbo(@PathVariable int id) {
        Verbo dados = verboRepository.findById(id).orElse(null);
        if (dados != null) {
            verboRepository.delete(dados);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
