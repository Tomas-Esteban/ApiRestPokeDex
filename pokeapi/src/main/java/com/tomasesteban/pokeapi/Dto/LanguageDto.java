package com.tomasesteban.pokeapi.Dto;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageDto {
	private String name;
    private String url;
}
