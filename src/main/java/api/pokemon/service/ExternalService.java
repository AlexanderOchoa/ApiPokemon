package api.pokemon.service;

import api.pokemon.response.external.EvolutionPokemonResponse;
import api.pokemon.response.external.GetDetailPokemonResponse;
import api.pokemon.response.external.ListPokemonResponse;

public interface ExternalService {
    ListPokemonResponse list(int limit, int offset);
    GetDetailPokemonResponse getDetail(String url);
    EvolutionPokemonResponse getEvolutions(String code);
}
