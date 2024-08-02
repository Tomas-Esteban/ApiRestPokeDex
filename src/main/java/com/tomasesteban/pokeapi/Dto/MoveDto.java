package com.tomasesteban.pokeapi.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoveDto {
    private String name;
    private String url;
}
