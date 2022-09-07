package api.pokemon.response;

import lombok.Data;

import java.util.List;

@Data
public class ListPokemonResponse {
    private List<PokemonDataResponse> data;

    @Data
    public class PokemonDataResponse {
        private String name;
        private String photo;
        private double weight;
        private List<String> types;
        private List<String> abilities;
        private List<String> evolutions;
    }
}
