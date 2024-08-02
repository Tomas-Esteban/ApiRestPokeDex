package com.tomasesteban.pokeapi.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "generations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Generation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generation_id")
    private Long id;

    @NotNull
    private String name;

    @OneToOne
    @JoinColumn(name = "region_id")
    private Region mainRegion;

    @OneToMany
    @JoinColumn(name = "generation_id") // FK de Ability
    private List<Ability> abilities;

    @OneToMany
    @JoinColumn(name = "generation_id") // FK de Move
    private List<Move> moves;

    @OneToMany
    @JoinColumn(name = "generation_id") // FK de Name 
    private List<Name> names;

    @OneToMany
    @JoinColumn(name = "generation_id") // FK de PokemonSpecies
    private List<PokemonSpecies> pokemonSpecies;

    @OneToMany
    @JoinColumn(name = "generation_id") // FK de Type
    private List<Type> types;

    @ElementCollection
    @CollectionTable(name = "generation_version_groups", joinColumns = @JoinColumn(name = "generation_id"))
    @Column(name = "version_group")
    private List<String> versionGroups;
}