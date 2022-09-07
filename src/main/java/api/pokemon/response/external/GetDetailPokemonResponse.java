package api.pokemon.response.external;

import lombok.Data;

import java.util.List;

@Data
public class GetDetailPokemonResponse {
    private String name;
    private double weight;
    private SpritesDetailPokemon sprites;
    private List<TypeGetDetailPokemon> types;
    private List<AbilityGetDetailPokemon> abilities;
}
