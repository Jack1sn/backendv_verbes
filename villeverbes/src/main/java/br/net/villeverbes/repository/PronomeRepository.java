package br.net.villeverbes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.net.villeverbes.model.Pronome;

public interface PronomeRepository extends JpaRepository<Pronome, Integer> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<Pronome> findByNome(String nome);
    public Optional<Pronome> findById(int id);
    public Optional<Pronome> findByPronome(String pronome);
    public List<Pronome> findAll();
    // Outros métodos de consulta podem ser definidos aqui
    @Query("SELECT p FROM Pronome p WHERE p.pronome = ?1")
    public Optional<Pronome> findByPronomeQuery(String pronome);

    //@Query("SELECT p FROM Pronome p WHERE p.id = ?")

}
