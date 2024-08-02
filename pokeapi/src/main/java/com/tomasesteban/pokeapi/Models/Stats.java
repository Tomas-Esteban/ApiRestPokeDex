package com.tomasesteban.pokeapi.Models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    
    @AttributeOverrides({
        @AttributeOverride(name = "increase", column = @Column(name = "increase_moves")),
        @AttributeOverride(name = "decrease", column = @Column(name = "decrease_moves"))
    })
    private String affectingMoves; 

    @AttributeOverrides({
        @AttributeOverride(name = "increase", column = @Column(name = "increase_natures")),
        @AttributeOverride(name = "decrease", column = @Column(name = "decrease_natures"))
    })
    private String affectingNatures;
    
    @ElementCollection
    @CollectionTable(name = "characteristics", joinColumns = @JoinColumn(name = "stats_id"))
    @Column(name = "characteristic")
    private List<String> characteristics;

    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "move_damage_class_name")),
        @AttributeOverride(name = "url", column = @Column(name = "move_damage_class_url"))
    })
    private String moveDamageClass; 

    @OneToMany
    @ElementCollection
    @CollectionTable(name = "names", joinColumns = @JoinColumn(name = "stats_id"))
    private List<Name> names; 

}