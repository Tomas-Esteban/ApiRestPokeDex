package com.tomasesteban.pokeapi.Models;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @NotNull
    private String name;
    private String url;

    @ElementCollection
    @CollectionTable(name = "type_damage_relations", joinColumns = @JoinColumn(name = "type_id"))
    @MapKeyColumn(name = "damage_relation_type")
    @Column(name = "damage_relation_url")
    private Map<String, String> damageRelations;

    @ElementCollection
    @CollectionTable(name = "type_past_damage_relations", joinColumns = @JoinColumn(name = "type_id"))
    @MapKeyColumn(name = "past_damage_relation_generation")
    @Column(name = "past_damage_relation_url")
    private Map<String, String> pastDamageRelations;

    @ElementCollection
    @CollectionTable(name = "type_game_indices", joinColumns = @JoinColumn(name = "type_id"))
    @MapKeyColumn(name = "game_index_generation")
    @Column(name = "game_index_url")
    private Map<String, String> gameIndices;

    @ElementCollection
    @CollectionTable(name = "type_generation", joinColumns = @JoinColumn(name = "type_id"))
    @MapKeyColumn(name = "generation_name")
    @Column(name = "generation_url")
    private Map<String, String> generation;

    @ElementCollection
    @CollectionTable(name = "type_move_damage_class", joinColumns = @JoinColumn(name = "type_id"))
    @MapKeyColumn(name = "move_damage_class_name")
    @Column(name = "move_damage_class_url")
    private Map<String, String> moveDamageClass;

    @ElementCollection
    @CollectionTable(name = "type_names", joinColumns = @JoinColumn(name = "type_id"))
    @MapKeyColumn(name = "name_language")
    @Column(name = "name_url")
    private Map<String, String> names;

    @ElementCollection
    @CollectionTable(name = "type_pokemon", joinColumns = @JoinColumn(name = "type_id"))
    @MapKeyColumn(name = "pokemon_name")
    @Column(name = "pokemon_url")
    private Map<String, String> pokemon;

    @ElementCollection
    @CollectionTable(name = "type_moves", joinColumns = @JoinColumn(name = "type_id"))
    @MapKeyColumn(name = "move_name")
    @Column(name = "move_url")
    private Map<String, String> moves;
}