package com.teste.springdevdojo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AnimePutRequestBody {
    private Long id;
	@NotEmpty(message="Anime não pode ser vazio")
	@NotNull(message="Anime não pode ser nulo")
    private String name;
}
