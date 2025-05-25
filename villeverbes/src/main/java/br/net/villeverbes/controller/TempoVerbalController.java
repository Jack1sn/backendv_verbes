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

import br.net.villeverbes.model.TempoVerbal;

@RestController
@CrossOrigin
public class TempoVerbalController {

    @Autowired
    private br.net.villeverbes.repository.TempoVerbalRepository tempoVerbalRepository;

    // Endpoint para obter todos os tempos verbais
    @GetMapping("/temposVerbais/all")
    public ResponseEntity<List<TempoVerbal>> getTemposVerbais() {
        List<TempoVerbal> dados = tempoVerbalRepository.findAll();
        if (dados != null) {
            return ResponseEntity.ok(dados);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint para obter um tempo verbal por ID
    @GetMapping("/temposVerbais/{id}")
    public ResponseEntity<TempoVerbal> getTempoVerbalById(@PathVariable int id) {
        TempoVerbal dados = tempoVerbalRepository.findById(id).orElse(null);
        if (dados != null) {
            return ResponseEntity.ok(dados);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/temposVerbais/post")
    public ResponseEntity<TempoVerbal> createTempoVerbal(@RequestBody TempoVerbal tempoVerbal) {
        TempoVerbal dados = tempoVerbalRepository.save(tempoVerbal);
        return ResponseEntity.ok(dados);
    }

    @PutMapping("/temposVerbais/put/{id}")
    public ResponseEntity<TempoVerbal> updateTempoVerbal(@PathVariable int id, @RequestBody TempoVerbal tempoVerbal) {
        TempoVerbal dados = tempoVerbalRepository.findById(id).orElse(null);
        if (dados != null) {
            dados.setTempoVerbal(tempoVerbal.getTempoVerbal());
            tempoVerbalRepository.save(dados);
            return ResponseEntity.ok(dados);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/temposVerbais/delete/{id}")
    public ResponseEntity<Void> deleteTempoVerbal(@PathVariable int id) {
        TempoVerbal dados = tempoVerbalRepository.findById(id).orElse(null);
        if (dados != null) {
            tempoVerbalRepository.delete(dados);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
