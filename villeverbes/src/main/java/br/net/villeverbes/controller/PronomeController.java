package br.net.villeverbes.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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

import com.example.crud_frases.model.Pronome;

@RestController
@CrossOrigin

public class PronomeController {
    @Autowired
    private com.example.crud_frases.repository.PronomeRepository pronomeRepository;

    // Endpoint para obter todos os pronomes
    @GetMapping("/pronomes/all")
    public ResponseEntity<List<Pronome>> getPronomes() {
        List<Pronome> dados = pronomeRepository.findAll();
        return ResponseEntity.ok(dados);
    }

    // Endpoint para obter um pronome por ID
    @GetMapping("/pronomes/{id}")
    public ResponseEntity<Pronome> getPronomeById(@PathVariable Integer id) {
        Pronome dados = pronomeRepository.findById(id).orElse(null);
        return ResponseEntity.ok(dados);
    }

    // Endpoint para criar um novo pronome
    // tamanho do char de pronome é 10
    /*
     * {
     * "pronome":"pronome"
     * }
     */
    @PostMapping("/pronomes/post")
    public ResponseEntity<Pronome> createPronome(@RequestBody Pronome pronome) {
        Pronome dados = pronomeRepository.save(pronome);
        return ResponseEntity.ok(dados);
    }

    // Endpoint para atualizar um pronome existente
    // nao precisa colocar o id no body
    /*
     * {
     * "pronome":"pronome"
     * }
     */
    @PutMapping("/pronomes/put/{id}")
    public ResponseEntity<Pronome> updatePronome(@PathVariable int id, @RequestBody Pronome pronome) {
        Pronome dados = pronomeRepository.findById(id).orElse(null);
        if (dados != null) {
            dados.setPronome(pronome.getPronome());
            pronomeRepository.save(dados);
            return ResponseEntity.ok(dados);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint para deletar um pronome existente
    // nao tem body
    @DeleteMapping("/pronomes/delete/{id}")
    public ResponseEntity<Void> deletePronome(@PathVariable int id) {
        if (pronomeRepository.existsById(id)) {
            pronomeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
