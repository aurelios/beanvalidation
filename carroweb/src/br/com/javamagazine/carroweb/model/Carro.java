package br.com.javamagazine.carroweb.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.javamagazine.carroweb.validator.ChecaDadosIniciaisTaxi;
import br.com.javamagazine.carroweb.validator.ValidadorDeEmail;

public class Carro implements Serializable {

	private static final long serialVersionUID = 2774613781099912281L;

	@NotNull(message = "Modelo não pode ficar em branco")
	@Size(min = 2, max = 40, message = "O modelo deve possuir entre {min} e {max} caracteres")
	private String modelo;

	@NotNull(message = "Placa não pode ficar em branco")
	@Pattern(regexp = "^[A-Z]{3}\\-\\d{4}", message = "Placa inválida")
	private String placa;

	@NotNull(message = "Cor não pode ficar em branco")
	@Size(min = 4, message = "A cor deve possuir no mínimo {min} caracteres")
	private String cor;

	@NotNull(message = "E-mail do Proprietário não pode ficar em branco")
	@ValidadorDeEmail
	private String emailProprietario;

	@Future(message = "A data da próxima revisão deve ser uma data futura")
	private Date dataProximaRevisao;

	@AssertFalse(message = "O carro não pode ser cadastrado como roubado")
	private Boolean roubado;

	@Max(value = 2013, message = "Ano de fabricação não pode ser maior que {value}. Você digitou "
			+ "${validatedValue}")
	private Integer anoFabricacao;

	@Max(value = 2014, message = "Ano do modelo não pode ser maior que {value}. Você digitou " + "${validatedValue}")
	private Integer anoModelo;

	@AssertTrue(message = "O campo taxi deve estar marcado", groups = ChecaDadosIniciaisTaxi.class)
	private Boolean taxi;

	private Motorista motorista = new Motorista();

	public Boolean getTaxi() {
		return taxi;
	}

	public void setTaxi(Boolean taxi) {
		this.taxi = taxi;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	@NotNull(message = "O retorno do método não pode ser nulo")
	public String retornaUsuarioDeEmail(String emailProp) {
		// retorna null se emailProp for null
		return (emailProp == null) ? null : emailProp.substring(0, emailProp.indexOf('@'));
	}

	public String retornaNumeroMesDataProximaRevisao(
			@NotNull(message = "Parâmetro data de revisão " + "no método não pode ser nulo") Date dataRevisao) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM", new Locale("pt", "BR"));
		return (sdf.format(dataRevisao));
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getEmailProprietario() {
		return emailProprietario;
	}

	public void setEmailProprietario(String emailProprietario) {
		this.emailProprietario = emailProprietario;
	}

	public Date getDataProximaRevisao() {
		return dataProximaRevisao;
	}

	public void setDataProximaRevisao(Date dataProximaRevisao) {
		this.dataProximaRevisao = dataProximaRevisao;
	}

	public Boolean getRoubado() {
		return roubado;
	}

	public void setRoubado(Boolean roubado) {
		this.roubado = roubado;
	}

	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Integer getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
