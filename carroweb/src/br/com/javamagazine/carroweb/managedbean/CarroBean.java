package br.com.javamagazine.carroweb.managedbean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import br.com.javamagazine.carroweb.model.Carro;
import br.com.javamagazine.carroweb.model.Motorista;
import br.com.javamagazine.carroweb.validator.ChecaDadosIniciaisTaxi;

@Named
@SessionScoped
public class CarroBean implements Serializable {

	private static final long serialVersionUID = -653606272761485391L;

	private Carro carro = new Carro();

	public String salvarCarro() {
		// se n�o houve problema na valida��o, vai chegar a este ponto
		carro = new Carro();

		// adiciona mensagem de sucesso
		FacesMessage fm = new FacesMessage();
		fm.setSummary("Sucesso ao salvar o carro");
		fm.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage("form", fm);

		return null;
	}

	public String simularErroEmailProprietario() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		ExecutableValidator executableValidator = factory.getValidator().forExecutables();
		Method method;
		Set<ConstraintViolation<Carro>> violations = null;

		try {
			method = Carro.class.getMethod("retornaUsuarioDeEmail", String.class);
			// Configura o e-mail do propriet�rio como nulo
			carro.setEmailProprietario(null);
			// Erro. E-mail do propriet�rio est� nulo, portanto retorno do
			// m�todo
			// vai ser nulo
			violations = executableValidator.validateReturnValue(carro, method, carro.getEmailProprietario());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}

		if (violations != null && violations.size() > 0) {
			// adiciona mensagem de erro
			FacesMessage fm = new FacesMessage();
			fm.setSummary(violations.iterator().next().getMessage());
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("form", fm);

			return null; // retorna null para exibir erro e permanecer na p�gina
		}

		return null; // �null� deve ser substitu�do pela p�gina de sucesso em um
						// cen�rio real
	}

	public String simularErroDataProximaRevisao() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		ExecutableValidator executableValidator = factory.getValidator().forExecutables();
		Object[] parameterValues = { carro.getDataProximaRevisao() };
		Method method;
		Set<ConstraintViolation<Carro>> violations = null;
		try {
			method = Carro.class.getMethod("retornaNumeroMesDataProximaRevisao", Date.class);
			// data da revis�o est� nulo, vai gerar viola��es
			violations = executableValidator.validateParameters(carro, method, parameterValues);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}

		if (violations.size() > 0) {
			// adiciona mensagem de erro
			FacesMessage fm = new FacesMessage();
			fm.setSummary(violations.iterator().next().getMessage());
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("form", fm);

			return null; // retorna null para exibir erro e permanecer na p�gina
		}

		return null; // �null� deve ser substitu�do pela p�gina de sucesso em um
						// cen�rio real
	}

	public String simularErroAgrupamento() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Carro>> violationsCarro = validator.validate(carro, ChecaDadosIniciaisTaxi.class);

		// Se o carro n�o estiver marcado como taxi, entra aqui
		if (violationsCarro != null && violationsCarro.size() > 0) {
			FacesMessage fm = new FacesMessage();
			fm.setSummary(violationsCarro.iterator().next().getMessage());
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("form", fm);
		}

		Set<ConstraintViolation<Motorista>> violationsMotorista = validator.validate(carro.getMotorista(),
				ChecaDadosIniciaisTaxi.class);

		// Se houver problema no preenchimento do campo anos de carteira do
		// motorista, entra aqui
		if (violationsMotorista != null && violationsMotorista.size() > 0) {
			FacesMessage fm = new FacesMessage();
			fm.setSummary(violationsMotorista.iterator().next().getMessage());
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("form", fm);
		}

		if (!violationsCarro.isEmpty() || !violationsMotorista.isEmpty()) {
			return null; // fica na p�gina se tiver erro nas valida��es
		} else {
			/*
			 * Chega aqui se n�o houver problema de valida��o. Deveria retornar
			 * a p�gina seguinte de cadastro do taxi em um cen�rio real
			 */
			return null;
		}
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

}