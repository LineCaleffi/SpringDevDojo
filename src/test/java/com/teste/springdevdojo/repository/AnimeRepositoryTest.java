package com.teste.springdevdojo.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

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

		// as Assertions servem para testar se o seu código esta de acordo com as regras
		// que deve seguir
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

		// as Assertions servem para testar se o seu código esta de acordo com as regras
		// que deve seguir
		Assertions.assertThat(animeUpdate).isNotNull();
		Assertions.assertThat(animeUpdate.getId()).isNotNull();
		Assertions.assertThat(animeUpdate.getName()).isEqualTo(animeSaved.getName());
	}

	@Test
	@DisplayName("Anime Deletado com sucesso")
	void deleteSuccess() {
		Anime anime = createAnime();
		Anime animeSaved = this.animeRepository.save(anime);

		this.animeRepository.delete(animeSaved);
		this.animeRepository.findById(animeSaved.getId());

		Optional<Anime> animeOp = this.animeRepository.findById(animeSaved.getId());
		Assertions.assertThat(animeOp.isEmpty()).isTrue();

	}

	@Test
	@DisplayName("Find by name success")
	void findByName_returnList_Success() {
		Anime anime = createAnime();
		Anime animeSaved = this.animeRepository.save(anime);

		String name = animeSaved.getName();
		List<Anime> animes = this.animeRepository.findByName(name);

		Assertions.assertThat(animes).isNotEmpty();
		Assertions.assertThat(animes).contains(animeSaved);
	}

	@Test
	@DisplayName("Retorna uma lista vazia quando nenhum anime for encontrado")
	void findByName_returnList_NotFound() {
		List<Anime> animes = this.animeRepository.findByName("xx");

		Assertions.assertThat(animes).isEmpty();
	} // aula 31

	private Anime createAnime() {
		return Anime.builder().name("Naruto").build();
	}

}
