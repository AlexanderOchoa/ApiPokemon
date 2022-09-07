package api.pokemon.response.external;

import lombok.Data;

import java.util.List;

@Data
public class EvolutionPokemonResponse {
    private int id;
    private String name;
    private List<EvolutionPokemon> pokemon_species;
}
