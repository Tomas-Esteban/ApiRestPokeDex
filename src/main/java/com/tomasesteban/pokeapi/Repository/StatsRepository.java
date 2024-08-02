package com.tomasesteban.pokeapi.Repository;

import com.tomasesteban.pokeapi.Models.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {
}
