package com.tomasesteban.pokeapi.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "pokemons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pokemon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pokemon_id")
    private Long id;
    
    @NotNull
    private String name;
    
    private int height;
    private int weight;
    
    @ElementCollection
    @MapKeyColumn(name = "stat_name")
    @Column(name = "stat_value")
    @CollectionTable(name = "pokemon_stats", joinColumns = @JoinColumn(name = "pokemon_id"))
    private Map<String, String> stats; 
    
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
    
    @ElementCollection
    @MapKeyColumn(name = "type_name")
    @Column(name = "type_value")
    @CollectionTable(name = "pokemon_types", joinColumns = @JoinColumn(name = "pokemon_id"))
    private Map<Integer, String> types;
    
    @Column(name = "picture_url")
    private String pictureUrl;
    
    @ElementCollection
    @CollectionTable(name = "pokemon_abilities", joinColumns = @JoinColumn(name = "pokemon_id"))
    @Column(name = "ability_name")
    private List<String> abilities;
    
    @ElementCollection
    @CollectionTable(name = "pokemon_moves", joinColumns = @JoinColumn(name = "pokemon_id"))
    @Column(name = "move_name")
    private List<String> moves; 
    
    @Column(name = "description")
    private String description; 
}