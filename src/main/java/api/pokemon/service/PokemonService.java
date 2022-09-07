package api.pokemon.service;

import api.pokemon.response.ListPokemonResponse;

public interface PokemonService {
    ListPokemonResponse list(int limit, int offset);
}
