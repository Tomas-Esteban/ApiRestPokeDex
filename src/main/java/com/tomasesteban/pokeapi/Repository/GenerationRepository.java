package com.tomasesteban.pokeapi.Repository;

import com.tomasesteban.pokeapi.Models.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Long> {
}