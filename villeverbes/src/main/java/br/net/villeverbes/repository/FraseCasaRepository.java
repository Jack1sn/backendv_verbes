package br.net.villeverbes.repository;

import br.net.villeverbes.entity.FraseAmbienteCasaEntity;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

public interface FraseCasaRepository extends JpaRepository<FraseAmbienteCasaEntity, Long> {

    @EntityGraph(attributePaths = {
        "pronomeTexto", "verboInfinitivo", "complementoDescricao", "tempo"
    })
    @NonNull
    List<FraseAmbienteCasaEntity> findAll();

    @EntityGraph(attributePaths = {
        "pronomeTexto", "verboInfinitivo", "complementoDescricao", "tempo"
    })
    List<FraseAmbienteCasaEntity> findByPronomeTexto(String texto);

    @EntityGraph(attributePaths = {
        "pronomeTexto", "verboInfinitivo", "complementoDescricao", "tempo"
    })
    List<FraseAmbienteCasaEntity> findByPronomeTextoAndComplementoDescricao(String pronome, String descricao);

    @EntityGraph(attributePaths = {
        "pronomeTexto", "verboInfinitivo", "complementoDescricao", "tempo"
    })
    @Query("SELECT f FROM FraseAmbienteCasaEntity f WHERE f.verboInfinitivo.verbo = :verboTexto")
    List<FraseAmbienteCasaEntity> findByVerboInfinitivoTexto(String verboTexto);

    @EntityGraph(attributePaths = {
        "pronomeTexto", "verboInfinitivo", "complementoDescricao", "tempo"
    })
    List<FraseAmbienteCasaEntity> findTop10ByOrderByIdDesc();

    @EntityGraph(attributePaths = {
        "pronomeTexto", "verboInfinitivo", "complementoDescricao", "tempo"
    })
    @Query("SELECT f FROM FraseAmbienteCasaEntity f WHERE f.verboInfinitivo.verbo = :verboTexto AND f.tempo = :tempo")
    List<FraseAmbienteCasaEntity> findByVerboInfinitivoTextoAndTempo(String verboTexto, String tempo);

    @Query("SELECT COUNT(f) FROM FraseAmbienteCasaEntity f WHERE f.verboInfinitivo.verbo = :verboTexto")
    Long countByVerboInfinitivoTexto(String verboTexto);

    @EntityGraph(attributePaths = {
        "pronomeTexto", "verboInfinitivo", "complementoDescricao", "tempo"
    })
    List<FraseAmbienteCasaEntity> findByVerboInfinitivoVerbo(String verbo);

    @Transactional
@Modifying
@Query("DELETE FROM FraseAmbienteCasaEntity f WHERE f.id = :id")
void deleteFraseById(@Param("id") Long id);

}
