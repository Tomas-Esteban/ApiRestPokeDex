package com.tomasesteban.pokeapi.Models;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "abilities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ability_id")
    private Long id;
    @NotNull
    private String name;
    private String url;
}