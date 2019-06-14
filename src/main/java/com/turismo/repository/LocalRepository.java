package com.turismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turismo.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> { }