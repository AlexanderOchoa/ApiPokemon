package api.pokemon.service.impl;

import api.pokemon.response.external.*;
import api.pokemon.service.ExternalService;
import api.pokemon.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith({SpringExtension.class})
class PokemonServiceImplTest {

    @MockBean
    private ExternalService externalService;

    @Autowired
    private PokemonService pokemonService;

    @Test
    void list() {
        ListPokemonResponse listPokemonResponse = new ListPokemonResponse();
        listPokemonResponse.setCount(100);
        listPokemonResponse.setNext("next");
        listPokemonResponse.setPrevious("previous");

        List<ResultListPokemon> listResultPokemon = new ArrayList<>();
        ResultListPokemon resultListPokemon = new ResultListPokemon();
        resultListPokemon.setName("Charmander");
        resultListPokemon.setUrl("https://pokemons");
        listResultPokemon.add(resultListPokemon);
        listPokemonResponse.setResults(listResultPokemon);

        GetDetailPokemonResponse getDetailPokemonResponse = new GetDetailPokemonResponse();
        getDetailPokemonResponse.setName("Charmander");
        getDetailPokemonResponse.setWeight(100);
        SpritesDetailPokemon spritesDetailPokemon = new SpritesDetailPokemon();
        spritesDetailPokemon.setBack_default("http://pokemons/5.png");
        getDetailPokemonResponse.setSprites(spritesDetailPokemon);

        List<TypeGetDetailPokemon> typeGetDetailPokemons = new ArrayList<>();
        TypeGetDetailPokemon typeGetDetailPokemon = new TypeGetDetailPokemon();
        TypeTypeGetDetailPokemon typeTypeGetDetailPokemon = new TypeTypeGetDetailPokemon();
        typeTypeGetDetailPokemon.setName("whater");
        typeTypeGetDetailPokemon.setUrl("http://pokemons/whater");
        typeGetDetailPokemon.setType(typeTypeGetDetailPokemon);
        typeGetDetailPokemons.add(typeGetDetailPokemon);
        getDetailPokemonResponse.setTypes(typeGetDetailPokemons);

        List<AbilityGetDetailPokemon> abilityGetDetailPokemons = new ArrayList<>();
        AbilityGetDetailPokemon abilityGetDetailPokemon = new AbilityGetDetailPokemon();
        AbilityAbilityGetDetailPokemon abilityAbilityGetDetailPokemon = new AbilityAbilityGetDetailPokemon();
        abilityAbilityGetDetailPokemon.setName("whater");
        abilityGetDetailPokemon.setAbility(abilityAbilityGetDetailPokemon);
        abilityGetDetailPokemons.add(abilityGetDetailPokemon);
        getDetailPokemonResponse.setAbilities(abilityGetDetailPokemons);

        EvolutionPokemonResponse evolutionPokemonResponse = new EvolutionPokemonResponse();
        evolutionPokemonResponse.setId(1);
        evolutionPokemonResponse.setName("Charmander");

        List<EvolutionPokemon> evolutionPokemons = new ArrayList<>();
        EvolutionPokemon evolutionPokemon = new EvolutionPokemon();
        evolutionPokemon.setName("Charmeleon");
        evolutionPokemons.add(evolutionPokemon);
        evolutionPokemonResponse.setPokemon_species(evolutionPokemons);

        when(externalService.list(anyInt(), anyInt()))
                .thenReturn(listPokemonResponse);
        when(externalService.getDetail(anyString()))
                .thenReturn(getDetailPokemonResponse);
        when(externalService.getEvolutions(anyString()))
                .thenReturn(evolutionPokemonResponse);

        api.pokemon.response.ListPokemonResponse response = pokemonService.list(5, 0);

        assertNotNull(response);
        assertEquals("Charmander", response.getData().get(0).getName());
        assertEquals(100, response.getData().get(0).getWeight());
    }
}