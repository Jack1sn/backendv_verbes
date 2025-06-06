package br.net.villeverbes.repository;

import br.net.villeverbes.entity.PronomeEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PronomeRepository extends JpaRepository<PronomeEntity, Long> {

     Optional<PronomeEntity> findByTexto(String texto);
}
