package api.pokemon.service.impl;

import api.pokemon.response.external.EvolutionPokemonResponse;
import api.pokemon.response.external.GetDetailPokemonResponse;
import api.pokemon.response.external.ListPokemonResponse;
import api.pokemon.service.ExternalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceImpl implements ExternalService {

    private Logger LOGGER = LoggerFactory.getLogger(ExternalServiceImpl.class);

    private RestTemplate restTemplate;

    @Value("${external.url}")
    private String externalUrl;

    @Autowired
    public ExternalServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ListPokemonResponse list(int limit, int offset) {
        String url = externalUrl + "/pokemon?limit=" + limit + "&offset=" + offset;

        LOGGER.info("Get external api pokemon list: url: {}", url);

        ResponseEntity<ListPokemonResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, getHeader(), ListPokemonResponse.class);
        ListPokemonResponse response = responseEntity.getBody();

        LOGGER.info("Response external api pokemon list: {}", response);

        return response;
    }

    @Override
    public GetDetailPokemonResponse getDetail(String url) {
        LOGGER.info("Get external api pokemon detail: url: {}", url);

        ResponseEntity<GetDetailPokemonResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, getHeader(), GetDetailPokemonResponse.class);
        GetDetailPokemonResponse response = responseEntity.getBody();

        LOGGER.info("Response external api pokemon detail: {}", response);

        return response;
    }

    @Override
    public EvolutionPokemonResponse getEvolutions(String code) {
        String url = externalUrl + "/evolution-trigger/" + code;

        LOGGER.info("Get external api pokemon get evolutions: url: {}", url);

        ResponseEntity<EvolutionPokemonResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, getHeader(), EvolutionPokemonResponse.class);
        EvolutionPokemonResponse response = responseEntity.getBody();

        LOGGER.info("Response external api pokemon get evolutions: {}", response);

        return response;
    }

    private HttpEntity getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<>(headers);
    }

}
