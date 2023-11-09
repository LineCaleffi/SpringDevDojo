package com.teste.springdevdojo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.springdevdojo.domain.Anime;
import com.teste.springdevdojo.request.AnimePutRequestBody;
import com.teste.springdevdojo.service.AnimeService;
import com.teste.springdevdojo.util.DateUtil;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("animes")
@Log4j2
public class AnimeController {
	@Autowired
	private DateUtil dateUtil;
	
	@Autowired
	private AnimeService animeService;

	@GetMapping
	public ResponseEntity<List<Anime>> listAll() {
		//log.info(dateUtil.formatLoalDateTime(LocalDateTime.now()));
		return ResponseEntity.ok(animeService.listAll());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Anime> findById(@PathVariable long id) {
		return ResponseEntity.ok(animeService.findById(id));
	}
	
	@GetMapping(path = "/find/{name}")
	public ResponseEntity<List<Anime>> findByName(@PathVariable String name) {
		return ResponseEntity.ok(animeService.findByName(name));
	}
	
	@PostMapping
	public ResponseEntity<Anime> save(@RequestBody Anime newAnime){
		return ResponseEntity.ok().body(animeService.save(newAnime));
	}
	
	@DeleteMapping(path = "/{id}")
	public  void delete(@PathVariable long id) {
		animeService.delete(id);
	}
	
	@PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequestBody animeUpdate) {
        animeService.update(animeUpdate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}