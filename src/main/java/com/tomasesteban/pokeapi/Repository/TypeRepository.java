package com.tomasesteban.pokeapi.Repository;

import com.tomasesteban.pokeapi.Models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}