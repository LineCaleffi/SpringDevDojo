package com.teste.springdevdojo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teste.springdevdojo.domain.Anime;
import com.teste.springdevdojo.exception.BadRequestException;
import com.teste.springdevdojo.repository.AnimeRepository;
import com.teste.springdevdojo.request.AnimePutRequestBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeService {
	@Autowired
	private AnimeRepository animeRepository;

//	public List<Anime> listAll() {
//		return animeRepository.findAll();
//	}
	
	public Page<Anime> listAll(Pageable pageable) {
		return animeRepository.findAll(pageable);
 	}

	public List<Anime> findByName(String name) {
		return animeRepository.findByName(name);
	}

	public Anime findById(long id) {
		return animeRepository.findById(id).orElseThrow(() -> new BadRequestException("Anime not Found"));
	}

	public void delete(long id) {
		animeRepository.deleteById(id);
	}
	
	@Transactional
	public Anime save(Anime animePostRequestBody) {
		return animeRepository.save(animePostRequestBody);
		
		// return
		// animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
	}
	
	public void update(AnimePutRequestBody animeUpdate) {
		Anime savedAnime = findById(animeUpdate.getId());
		Anime anime = Anime.builder().id(savedAnime.getId()).name(animeUpdate.getName()).build();
		//Anime anime = AnimeMapper.INSTANCE.toAnime(animeUpdate);
		//anime.setId(savedAnime.getId());
		
		animeRepository.save(anime);
	}

}
