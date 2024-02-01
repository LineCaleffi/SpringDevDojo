package com.teste.springdevdojo.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.teste.springdevdojo.domain.Anime;

@DataJpaTest
//@DisplayName("AnimeRepositoryTest")
class AnimeRepositoryTest {
	@Autowired
	private AnimeRepository animeRepository;

	@Test
	@DisplayName("Anime salvo com sucesso")
	void saveSuccess() {
		Anime anime = createAnime();
		Anime animeSaved = this.animeRepository.save(anime);
		
		//  as Assertions servem para testar se o seu código esta de acordo com as regras que deve seguir
		Assertions.assertThat(animeSaved).isNotNull();
		Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeSaved.getName());	
    }
	
	@Test
	@DisplayName("Anime ALTERADO com sucesso")
	void updateSuccess() {
		Anime anime = createAnime();
		Anime animeSaved = this.animeRepository.save(anime);
		
		animeSaved.setName("Avatar: A Lenda de AANG");
		
		Anime animeUpdate = this.animeRepository.save(animeSaved);
		
		//  as Assertions servem para testar se o seu código esta de acordo com as regras que deve seguir
		Assertions.assertThat(animeSaved).isNotNull();
		Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeSaved.getName());	
    }
	

	private Anime createAnime() {
		return Anime.builder().name("Naruto").build();
	}
}
