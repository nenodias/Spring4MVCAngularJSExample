package br.org.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AppModel<T, PK> {

	@JsonIgnore
	PK getPK();
	void merge(T other);
}
