package br.org.springmvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "pessoa")
public class Pessoa implements AppModel<Pessoa, Long>, Serializable{
	
	private static final long serialVersionUID = 7250869225401865610L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Size(max = 60)
	@Column(length = 60, nullable = false)
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pessoa){
			Pessoa other = (Pessoa)obj;
			return new EqualsBuilder()
			.append(id, other.id)
			.append(nome, other.nome)
			.isEquals();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(id)
		.append(nome)
		.toHashCode()
		;
	}

	@Override
	public Long getPK() {
		return id;
	}
	@Override
	public void merge(Pessoa other) {
		this.nome = other.nome;		
	}
}
