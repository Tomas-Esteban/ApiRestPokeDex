package com.tomasesteban.pokeapi.Models;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @NotNull
    private String name;
    private String url;
}
