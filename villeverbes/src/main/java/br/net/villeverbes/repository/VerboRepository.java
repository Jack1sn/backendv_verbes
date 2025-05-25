package br.net.villeverbes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.villeverbes.model.Verbo;

public interface VerboRepository extends JpaRepository<Verbo, Integer> {
    // Métodos de consulta para a entidade Verbo
    public Optional<Verbo> findById(int id);

    public Optional<Verbo> findByVerbo(String verbo);
    public List<Verbo> findAll();
    


}
