package com.teste.springdevdojo.request;

import lombok.Data;

@Data
public class AnimePostRequestBody {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
