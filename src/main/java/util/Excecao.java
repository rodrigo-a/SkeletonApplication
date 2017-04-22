package util;

import javax.faces.application.FacesMessage;

public class Excecao extends Exception {

	private static final long serialVersionUID = 1L;

	public Excecao(String msg) {
		super(msg);
	}

	private FacesMessage.Severity severidade;
	private String sumario;

	public Excecao(FacesMessage.Severity severidade, String sumario, String msg) {
		super(msg);
		this.severidade = severidade;
		this.sumario = sumario;
	}

	public FacesMessage getFacesMessage() {
		return new FacesMessage(this.severidade == null ? FacesMessage.SEVERITY_INFO : this.severidade,
				this.sumario == null ? "" : this.sumario, super.getMessage());
	}

	public FacesMessage.Severity getSeveridade() {
		return severidade;
	}

	public String getSumario() {
		return sumario;
	}

}
