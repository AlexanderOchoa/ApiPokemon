package api.pokemon.response.external;

import lombok.Data;

import java.util.List;

@Data
public class ListPokemonResponse {
    private int count;
    private String next;
    private String previous;
    private List<ResultListPokemon> results;
}
