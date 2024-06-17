package com.teste.springdevdojo.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.teste.springdevdojo.domain.Anime;
import com.teste.springdevdojo.service.AnimeService;
import com.teste.springdevdojo.util.AnimeCreator;

@ExtendWith(SpringExtension.class)
@DisplayName("AnimeControllerTest")
public class AnimeControllerTest {
	@InjectMocks // usado quando você quer testar a classe em si
	private AnimeController animeController;
	
	@Mock
	private AnimeService animeService;
	
	@BeforeEach
	void setUp() {
		PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createAnimeValido()));
		BDDMockito.when(animeService.listAll(ArgumentMatchers.any())).thenReturn(animePage); //quando executar uma chamada para listAll() retorna animePage
	}
	
	@Test
	@DisplayName("Retorna lista de animes dentro do objeto da página quando bem-sucedido")
	void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful() {
		String expectedName = AnimeCreator.createAnimeValido().getName();
		Page<Anime> animePage = animeController.listAll(null).getBody();
		
		Assertions.assertThat(animePage).isNotNull();
		Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
		Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
	}
}

/*
 * Diferença entre @InjectMocks e @Mock
 * 		@InjectMocks: 
 * 			É usada quando queremos criar uma instância real da classe em si.
 * 			Ela injeta os mocks que são criados com as anotações @Mock.
 * 
 * 		@Mock: 
 * 			É usado para criar um objeto simulado (mock) de uma classe ou interface em um teste. 
 * 			Ela permite simular o comportamento de um objeto real durante a execução do teste, sem a necessidade de criar uma instância real da classe.
 * 
 */
