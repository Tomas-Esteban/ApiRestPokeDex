package com.tomasesteban.pokeapi.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
    private Map<String,String> stats;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
    
    @ElementCollection
	@CollectionTable(name = "pokemon_types", joinColumns = @JoinColumn(name = "pokemon_id"))
	@MapKeyColumn(name = "types_name")
	@Column(name = "types_url")
    private Map<Integer, String> types;
   
}