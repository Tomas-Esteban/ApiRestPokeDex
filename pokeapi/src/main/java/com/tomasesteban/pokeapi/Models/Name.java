package com.tomasesteban.pokeapi.Models;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@Entity
@Table(name = "names")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Name {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_id")
    private Long id;

    @NotNull
    private String name;

    @OneToOne
    @JoinColumn(name = "language_id")
    private Language language;
}