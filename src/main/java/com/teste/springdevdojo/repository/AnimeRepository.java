package com.teste.springdevdojo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.springdevdojo.domain.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
	//List<Anime> listAll();
	List<Anime> findByName(String name);
}
