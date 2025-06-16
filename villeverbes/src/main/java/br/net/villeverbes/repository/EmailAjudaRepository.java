package br.net.villeverbes.repository;

import br.net.villeverbes.entity.EmailAjudaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmailAjudaRepository extends JpaRepository<EmailAjudaEntity, Long> {

   @Query("SELECT COUNT(e) > 0 FROM EmailAjudaEntity e WHERE e.resposta IS NULL OR e.resposta = ''")
// Forma correta com dois OR
boolean existsByRespostaIsNullOrRespostaEquals(String empty);
Long countByRespostaIsNullOrRespostaEquals(String respostaVazia);


}
