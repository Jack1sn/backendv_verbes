package br.net.villeverbes.repository;

import br.net.villeverbes.entity.FraseAmbienteCasaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FraseCasaRepository extends JpaRepository<FraseAmbienteCasaEntity, Long> {

    @EntityGraph(attributePaths = {
        "pronomeTexto", "verboInfinitivo", "complementoDescricao", "tempo"
    })
    @NonNull
    List<FraseAmbienteCasaEntity> findAll();

    List<FraseAmbienteCasaEntity> findByPronomeTexto(String texto);

    List<FraseAmbienteCasaEntity> findByPronomeTextoAndComplementoDescricao(String pronome, String descricao);

    // Corrigido: usando verboInfinitivo.verbo, não .texto
    @Query("SELECT f FROM FraseAmbienteCasaEntity f WHERE f.verboInfinitivo.verbo = :verboTexto")
    List<FraseAmbienteCasaEntity> findByVerboInfinitivoTexto(String verboTexto);

    List<FraseAmbienteCasaEntity> findTop10ByOrderByIdDesc();

    @Query("SELECT f FROM FraseAmbienteCasaEntity f WHERE f.verboInfinitivo.verbo = :verboTexto AND f.tempo = :tempo")
    List<FraseAmbienteCasaEntity> findByVerboInfinitivoTextoAndTempo(String verboTexto, String tempo);

    @Query("SELECT COUNT(f) FROM FraseAmbienteCasaEntity f WHERE f.verboInfinitivo.verbo = :verboTexto")
    Long countByVerboInfinitivoTexto(String verboTexto);

    // Alternativa sem usar @Query (Spring Data query derivation)
    List<FraseAmbienteCasaEntity> findByVerboInfinitivoVerbo(String verbo);
}
