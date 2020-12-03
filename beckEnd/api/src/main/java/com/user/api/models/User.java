package com.user.api.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.util.StringUtils;

import com.sun.istack.NotNull;


@Entity
@Table(name = "Tb_User")
public class User implements Serializable {

	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@NotBlank (message = "Campo inválido")
	private String nome;
	
	@NotNull
	@NotBlank (message = "Campo inválido")
	@Size(max = 2)
	private String idade;
	
	
	@CPF (message = "CPF inválido")
	private String cpf;
	
	@NotNull
	@NotBlank (message = "Verifique Campos de Endereço[Rua, Bairro ou Número]")
	private String endereco;
	
	@NotNull 
	@NotBlank (message = "Campo inválido")
	@Size(max = 1)
	private String sexo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = StringUtils.capitalize(nome);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = StringUtils.capitalize(endereco);
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	
}
