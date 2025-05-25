package br.net.villeverbes.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.net.villeverbes.model.PronomeVerboTempoVerbal;
import br.net.villeverbes.model.PronomeVerboTempoVerbal;
import br.net.villeverbes.model.Pronome;
import br.net.villeverbes.model.Verbo;

public interface PronomeVerboTempoVerbalRepository extends JpaRepository<PronomeVerboTempoVerbal, Integer> {
    // Métodos de consulta para a entidade PronomeVerboTempoVerbal
    public Optional<PronomeVerboTempoVerbal> findById(int id);
    
    //public Optional<PronomeVerboTempoVerbal> findByPronomeIdAndVerboIdAndTempoVerbalId(int pronomeId, int verboId, int tempoVerbalId);
    public List<PronomeVerboTempoVerbal> findAll();

    //public Optional<PronomeVerboTempoVerbal> findByVerboId(int idVerbo);


}