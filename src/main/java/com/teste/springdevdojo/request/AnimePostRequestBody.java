package com.teste.springdevdojo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostRequestBody {
	@NotEmpty(message="Anime não pode ser vazio")
	@NotNull(message="Anime não pode ser nulo")
	private String name;
}
