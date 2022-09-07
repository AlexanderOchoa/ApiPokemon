package api.pokemon.service.impl;

import api.pokemon.response.external.*;
import api.pokemon.service.ExternalService;
import api.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private ExternalService externalService;

    @Autowired
    public PokemonServiceImpl(ExternalService externalService) {
        this.externalService = externalService;
    }

    @Override
    public api.pokemon.response.ListPokemonResponse list(int limit, int offset) {
        ListPokemonResponse listPokemonExternalResponse = externalService.list(limit, offset);

        api.pokemon.response.ListPokemonResponse listPokemonResponse = new api.pokemon.response.ListPokemonResponse();

        List<api.pokemon.response.ListPokemonResponse.PokemonDataResponse> list = listPokemonExternalResponse.getResults().stream().map(
                data -> {
                    GetDetailPokemonResponse getDetailPokemonResponse = externalService.getDetail(data.getUrl());

                    api.pokemon.response.ListPokemonResponse.PokemonDataResponse pokemonDataResponse = listPokemonResponse.new PokemonDataResponse();
                    pokemonDataResponse.setName(data.getName());
                    pokemonDataResponse.setWeight(getDetailPokemonResponse.getWeight());
                    pokemonDataResponse.setAbilities(
                            getDetailPokemonResponse.getAbilities()
                                    .stream()
                                    .map(ability -> ability.getAbility().getName())
                                    .collect(Collectors.toList())
                    );
                    pokemonDataResponse.setTypes(
                            getDetailPokemonResponse.getTypes()
                                    .stream()
                                    .map(type -> type.getType().getName())
                                    .collect(Collectors.toList())
                    );
                    pokemonDataResponse.setPhoto(getDetailPokemonResponse.getSprites().getBack_default());
                    pokemonDataResponse.setEvolutions(externalService.getEvolutions(getCodeFromUri(data.getUrl())).getPokemon_species()
                            .stream()
                            .map(EvolutionPokemon::getName)
                            .collect(Collectors.toList())
                    );

                    return pokemonDataResponse;
                }
        ).collect(Collectors.toList());

        listPokemonResponse.setData(list);

        return listPokemonResponse;
    }

    private String getCodeFromUri(String url) {
        String[] elements = url.split("pokemon");
        return elements[1].replace("/", "");
    }

}
