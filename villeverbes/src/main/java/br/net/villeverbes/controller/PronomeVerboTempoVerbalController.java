package br.net.villeverbes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.villeverbes.model.PronomeVerboTempoVerbal;
import br.net.villeverbes.model.Verbo;

@RestController
@CrossOrigin
public class PronomeVerboTempoVerbalController {

    @Autowired
    private br.net.villeverbes.repository.PronomeVerboTempoVerbalRepository pronomeVerboTempoVerbalRepository;

    @GetMapping("/pronomeVerboTempoVerbal/all")
    public ResponseEntity<List<PronomeVerboTempoVerbal>> getPronomes() {
        List<PronomeVerboTempoVerbal> dados = pronomeVerboTempoVerbalRepository.findAll();
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/pronomeVerboTempoVerbal/{id}")
    public ResponseEntity<PronomeVerboTempoVerbal> getPronomeVerboTempoVerbalById(@PathVariable int id) {
        PronomeVerboTempoVerbal dados = pronomeVerboTempoVerbalRepository.findById(id).orElse(null);
        if (dados != null) {
            return ResponseEntity.ok(dados);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/pronomeVerboTempoVerbal/put/{id}")
    public ResponseEntity<PronomeVerboTempoVerbal> updatePronomeVerboTempoVerbal(@PathVariable int id,
            @RequestBody PronomeVerboTempoVerbal pronomeVerboTempoVerbal) {
        PronomeVerboTempoVerbal dados = pronomeVerboTempoVerbalRepository.findById(id).orElse(null);
        if (dados != null) {

            dados.setPronome(pronomeVerboTempoVerbal.getPronome());
            dados.setVerbo(pronomeVerboTempoVerbal.getVerbo());
            dados.setTempoVerbal(pronomeVerboTempoVerbal.getTempoVerbal());
            dados.setVerboConjugado(pronomeVerboTempoVerbal.getVerboConjugado());
            pronomeVerboTempoVerbalRepository.save(dados);
            return ResponseEntity.ok(dados);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/pronomeVerboTempoVerbal/post")
    public ResponseEntity<PronomeVerboTempoVerbal> createPronomeVerboTempoVerbal(
            @RequestBody PronomeVerboTempoVerbal pronomeVerboTempoVerbal) {
        PronomeVerboTempoVerbal dados = pronomeVerboTempoVerbalRepository.save(pronomeVerboTempoVerbal);
        return ResponseEntity.ok(dados);
    }

    /*
     * @GetMapping("/pronomeVerboTempoVerbal/verbos/{idVerbo}")
     * public ResponseEntity<List<PronomeVerboTempoVerbal>>
     * getPronomesByVerboId(@PathVariable int idVerbo) {
     * List<PronomeVerboTempoVerbal> dados =
     * pronomeVerboTempoVerbalRepository.findByVerboId(idVerbo);
     * return ResponseEntity.ok(dados);
     * }
     */

    /*
     * @GetMapping("/verbos/all")
     * public ResponseEntity<List<Verbo>> getVerbos() {
     * List<Verbo> dados = verboRepository.findAll();
     * return ResponseEntity.ok(dados);
     * }
     */
}
