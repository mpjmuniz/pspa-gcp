package org.pspa.gcp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Marcelo Pablo
 * 
 * @version 0.1
 * */
@Entity
public class ConteudoDidatico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String conteudo,
				   materialUsado,
				   etapas,
				   objetivos,
				   obsAvaliacao;

	public ConteudoDidatico(String conteudo, String materialUsado, String etapas, String objetivos,
			String obsAvaliacao) {
		super();
		this.conteudo = conteudo;
		this.materialUsado = materialUsado;
		this.etapas = etapas;
		this.objetivos = objetivos;
		this.obsAvaliacao = obsAvaliacao;
	}

	public ConteudoDidatico() {
		this("","","","","");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getMaterialUsado() {
		return materialUsado;
	}

	public void setMaterialUsado(String materialUsado) {
		this.materialUsado = materialUsado;
	}

	public String getEtapas() {
		return etapas;
	}

	public void setEtapas(String etapas) {
		this.etapas = etapas;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public String getObsAvaliacao() {
		return obsAvaliacao;
	}

	public void setObsAvaliacao(String obsAvaliacao) {
		this.obsAvaliacao = obsAvaliacao;
	}
	
	
}
