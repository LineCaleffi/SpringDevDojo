package com.teste.springdevdojo.mapper;

import com.teste.springdevdojo.domain.Anime;
import com.teste.springdevdojo.request.AnimePostRequestBody;
import com.teste.springdevdojo.request.AnimePutRequestBody;

public abstract class AnimeMapper{
	//public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

	public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

	public abstract Anime toAnime(AnimePutRequestBody animePostRequestBody);
}

// essa classe serve como um mediador entre os objetos em memória e o banco de dados. 