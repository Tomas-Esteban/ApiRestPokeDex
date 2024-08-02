package com.tomasesteban.pokeapi.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameDto {
	private String name;
    private LanguageDto language;
}
