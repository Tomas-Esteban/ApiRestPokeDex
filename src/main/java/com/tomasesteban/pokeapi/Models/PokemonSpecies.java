package com.tomasesteban.pokeapi.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "pokemon_species")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public  class PokemonSpecies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pokemon_species_id")
    private Long id;

    @NotNull
    private String name;
    private String url;
    
}
