package br.com.javamagazine.carroweb.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.javamagazine.carroweb.validator.ChecaDadosComplementaresTaxi;
import br.com.javamagazine.carroweb.validator.ChecaDadosIniciaisTaxi;

public class Motorista implements Serializable {

	private static final long serialVersionUID = -1749080756327279192L;

	private String nome;
	private Integer anosCarteira;

	@NotNull(message = "Nome do motorista n�o pode ficar em branco", groups = ChecaDadosComplementaresTaxi.class)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull(message = "Anos de carteira n�o pode ficar em branco", groups = ChecaDadosIniciaisTaxi.class)
	@Min(value = 5, message = "O motorista deve ter acima de {value} anos de experi�ncia", groups = ChecaDadosIniciaisTaxi.class)
	public Integer getAnosCarteira() {
		return anosCarteira;
	}

	public void setAnosCarteira(Integer anosCarteira) {
		this.anosCarteira = anosCarteira;
	}
}