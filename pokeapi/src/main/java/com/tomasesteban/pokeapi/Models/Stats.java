package com.tomasesteban.pokeapi.Models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity(name = "stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stats {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stats_id")
    private Long id;

    @NotNull
    private String name;  

    @Column(name = "game_index")
    private int gameIndex; 

    @Column(name = "is_battle_only")
    private boolean isBattleOnly; 

    @ElementCollection
    @CollectionTable(name = "affecting_moves", joinColumns = @JoinColumn(name = "stats_id"))
    @MapKeyColumn(name = "affect_type")
    @Column(name = "move")
    private Map<String,String> affectingMoves; 

    @ElementCollection
    @CollectionTable(name = "affecting_natures", joinColumns = @JoinColumn(name = "stats_id"))
    @MapKeyColumn(name = "affect_type")
    @Column(name = "nature")
    private Map<String,String> affectingNatures;
    
    @ElementCollection
    @CollectionTable(name = "characteristics", joinColumns = @JoinColumn(name = "stats_id"))
    @Column(name = "characteristic_url")
    private List<String> characteristics;

    @ElementCollection
	@CollectionTable(name = "stat_moveDamageClass", joinColumns = @JoinColumn(name = "stats_id"))
	@MapKeyColumn(name = "moveDamageClass_name")
	@Column(name = "moveDamageClass_url")
    private Map<String,String> moveDamageClass; 

    @ElementCollection
   	@CollectionTable(name = "stat_names", joinColumns = @JoinColumn(name = "stats_id"))
   	@MapKeyColumn(name = "names_name")
   	@Column(name = "names_url")
    private Map<String,String> names;

}