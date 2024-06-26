package com.teste.springdevdojo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostRequestBody {
	@NotEmpty(message="O nome do anime não pode ser vazio")
	@NotNull(message="O nome do anime não pode ser nulo")
	private String name;
	
	@URL(message="A URL é inválida")
	private String url;
}
