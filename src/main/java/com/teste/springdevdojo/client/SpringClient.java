package com.teste.springdevdojo.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.teste.springdevdojo.domain.Anime;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SpringClient {
	public static void main(String[] args) {
		ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class,2);
		log.info(entity);

		// Deste jeito mostra apenas 1 objeto da lista no console, no caso o ID 2 - Naruto
		Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 2);
		log.info(object);

		// Deste jeito mostra todos os objetos da lista, no caso todos os animes no console
		Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
		log.info(Arrays.toString(animes));

		// outra forma de puxar os dados usando restTemplate().exchange, desta forma tem mais flexibilidade do que o getForObject
		ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
				HttpMethod.GET, null, new ParameterizedTypeReference<>() {
				});
		log.info(exchange.getBody());

		/*--------- SERVE PARA ADICIONAR UM NOVO ANIME NO BD --------------*/
		Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
		ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("http://localhost:8080/animes/",
				HttpMethod.POST, new HttpEntity<>(samuraiChamploo, createJsonHeader()), Anime.class);

		log.info("saved anime {}", samuraiChamplooSaved);

	}

	private static HttpHeaders createJsonHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}
}

/*
 * RestTemplate: você pode usar quando precisar fazer uma chamada pontual a um
 * serviço externo a sua aplicação.
 * 
 * Permite interagir com serviços RESTful
 * 
 * Anime kingdom = Anime.builder().name("kingdom").build(); Anime kingdomSaved =
 * new RestTemplate().postForObject("http://localhost:8080/animes/", kingdom,
 * Anime.class); log.info("saved anime {}", kingdomSaved);
 */
