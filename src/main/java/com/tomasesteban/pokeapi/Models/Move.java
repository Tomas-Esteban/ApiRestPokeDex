package com.tomasesteban.pokeapi.Models;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@Entity
@Table(name = "moves")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "move_id")
    private Long id;
    @NotNull
    private String name;
    private String url;
}