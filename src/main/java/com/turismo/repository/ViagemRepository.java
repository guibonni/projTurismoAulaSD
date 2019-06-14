package com.turismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turismo.model.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Integer> { }