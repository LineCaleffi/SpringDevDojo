package com.teste.springdevdojo.util;

import com.teste.springdevdojo.domain.Anime;

public class AnimeCreator {
	
	public static Anime createAnime() {
		return Anime.builder().name("Naruto").build();
	}
	
	public static Anime createAnimeValido() {
		return Anime.builder().name("Naruto").id(1L).build();
	}
	
	public static Anime updateAnimeValido() {
		return Anime.builder().name("Naruto 2").id(1L).build();
	}
}
