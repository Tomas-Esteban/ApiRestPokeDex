package com.tomasesteban.pokeapi.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "regions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private Long id;

	@NotNull
	private String name;

	@ElementCollection
	@CollectionTable(name = "region_locations", joinColumns = @JoinColumn(name = "region_id"))
	@MapKeyColumn(name = "location_name")
	@Column(name = "location_url")
	private Map<String, String> locations;

	@ElementCollection
	@CollectionTable(name = "region_names", joinColumns = @JoinColumn(name = "region_id"))
	@MapKeyColumn(name = "language_name")
	@Column(name = "name")
	private Map<String, String> names;

	@ElementCollection
	@CollectionTable(name = "region_pokedexes", joinColumns = @JoinColumn(name = "region_id"))
	@MapKeyColumn(name = "pokedex_name")
	@Column(name = "pokedex_url")
	private Map<String, String> pokedexes;

	@ElementCollection
	@CollectionTable(name = "region_version_groups", joinColumns = @JoinColumn(name = "region_id"))
	@MapKeyColumn(name = "version_group_name")
	@Column(name = "version_group_url")
	private Map<String, String> versionGroups;

	@ElementCollection
	@CollectionTable(name = "region_generation", joinColumns = @JoinColumn(name = "region_id"))
	@MapKeyColumn(name = "generation_name")
	@Column(name = "generation_url")
	private Map<String, String> generation;
	
	
}