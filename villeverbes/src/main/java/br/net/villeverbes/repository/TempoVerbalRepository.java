package br.net.villeverbes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.net.villeverbes.model.TempoVerbal;


public interface TempoVerbalRepository extends JpaRepository<TempoVerbal, Integer> {
    // Métodos de consulta para a entidade TempoVerbal
    public Optional<TempoVerbal> findById(int id);
    public Optional<TempoVerbal> findByTempoVerbal(String tempoVerbal);
    public List<TempoVerbal> findAll();
    


}
