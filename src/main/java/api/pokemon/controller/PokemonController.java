package api.pokemon.controller;

import api.pokemon.response.ListPokemonResponse;
import api.pokemon.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/pokemons")
public class PokemonController {

    private Logger LOGGER = LoggerFactory.getLogger(PokemonController.class);

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<ListPokemonResponse> list(@RequestParam int limit, @RequestParam int offset) {
        LOGGER.info("Start to list");
        ListPokemonResponse response = pokemonService.list(limit, offset);
        LOGGER.info("End to list");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}