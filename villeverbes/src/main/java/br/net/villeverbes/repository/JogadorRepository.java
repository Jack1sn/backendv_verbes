package br.net.villeverbes.repository;

import br.net.villeverbes.entity.JogadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<JogadorEntity, Long> {
}
