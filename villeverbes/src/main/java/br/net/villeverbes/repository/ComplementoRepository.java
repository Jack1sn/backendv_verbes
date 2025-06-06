package br.net.villeverbes.repository;


import br.net.villeverbes.entity.ComplementoEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplementoRepository extends JpaRepository<ComplementoEntity, Long> {


 Optional<ComplementoEntity> findByDescricao(String descricao);}
