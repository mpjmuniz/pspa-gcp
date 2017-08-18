package org.pspa.gcp.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.pspa.gcp.modelo.enums.Casa;
import org.pspa.gcp.modelo.enums.CondicaoLocal;
import org.pspa.gcp.modelo.enums.MaterialMoradia;
import org.pspa.gcp.modelo.enums.Sexo;


/**
 * Entidade que representa os alunos inscritos, candidatos à vagas
 * 
 * @author Marcelo Pablo 
 * 
 * @version 0.1
 * */
@Entity
public class Inscrito{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;
	
	private String nome;

	
	private String nome_da_crianca;
	private LocalDate data_de_nascimento;
	private Sexo sexo;

	private String mae;
	private Integer idade_mae;
	private String trabalho_mae;

	private String pai;
	private Integer idade_pai;
	private String trabalho_pai;

	private String endereco;
	private String ponto_de_referência;

	private String telefone;
	private String objetivo_da_matrícula;

	private String responsavel;
	private String coordenadora;

	private LocalDate data;

	private Boolean visitado;
	
	public Inscrito() {
		this("", null, null, "", 0, "", "", 0, "", "",
				"", "", "", "", "", null, false);
	}

	public Inscrito(String nome_da_crianca, LocalDate data_de_nascimento, Sexo sexo, String mae, Integer idade_mae,
			String trabalho_mae, String pai, Integer idade_pai, String trabalho_pai, String endereco,
			String ponto_de_referência, String telefone, String objetivo_da_matrícula, String responsavel,
			String coordenadora, LocalDate data, Boolean visitado) {
		this.nome_da_crianca = nome_da_crianca;
		this.data_de_nascimento = data_de_nascimento;
		this.sexo = sexo;
		this.mae = mae;
		this.idade_mae = idade_mae;
		this.trabalho_mae = trabalho_mae;
		this.pai = pai;
		this.idade_pai = idade_pai;
		this.trabalho_pai = trabalho_pai;
		this.endereco = endereco;
		this.ponto_de_referência = ponto_de_referência;
		this.telefone = telefone;
		this.objetivo_da_matrícula = objetivo_da_matrícula;
		this.responsavel = responsavel;
		this.coordenadora = coordenadora;
		this.data = data;
		this.visitado = visitado;
	}

	// Sobre a visita domiciliar

	private Casa casa;
	private Integer quantidade_de_comodos;
	private MaterialMoradia tipo_de_moradia;
	private CondicaoLocal condicao_do_local;

	private String trabalho_pai_visita; // novamente?
	private String trabalho_mae_visita;

	private String a_crianca_ja_teve_alguma_doenca_grave;

	private String tem_alergia;
	private String carteira_vacinacao;

	private String observacao;

	public void visitar(Casa casa, Integer quantidade_de_comodos, MaterialMoradia tipo_de_moradia,
			CondicaoLocal condicao_do_local, String trabalho_pai_visita, String trabalho_mae_visita,
			String a_crianca_ja_teve_alguma_doenca_grave, String tem_alergia, String carteira_vacinacao,
			String observacao) {
		this.casa = casa;
		this.quantidade_de_comodos = quantidade_de_comodos;
		this.tipo_de_moradia = tipo_de_moradia;
		this.condicao_do_local = condicao_do_local;
		this.trabalho_pai_visita = trabalho_pai_visita;
		this.trabalho_mae_visita = trabalho_mae_visita;
		this.a_crianca_ja_teve_alguma_doenca_grave = a_crianca_ja_teve_alguma_doenca_grave;
		this.tem_alergia = tem_alergia;
		this.carteira_vacinacao = carteira_vacinacao;
		this.observacao = observacao;
	}

	public String getNome_da_crianca() {
		return nome_da_crianca;
	}

	public LocalDate getData_de_nascimento() {
		return data_de_nascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public String getMae() {
		return mae;
	}

	public Integer getIdade_mae() {
		return idade_mae;
	}

	public String getTrabalho_mae() {
		return trabalho_mae;
	}

	public String getPai() {
		return pai;
	}

	public Integer getIdade_pai() {
		return idade_pai;
	}

	public String getTrabalho_pai() {
		return trabalho_pai;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getPonto_de_referência() {
		return ponto_de_referência;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getObjetivo_da_matrícula() {
		return objetivo_da_matrícula;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public String getCoordenadora() {
		return coordenadora;
	}

	public LocalDate getData() {
		return data;
	}

	public Boolean getVisitado() {
		return visitado;
	}

	public Casa getCasa() {
		return casa;
	}

	public Integer getQuantidade_de_comodos() {
		return quantidade_de_comodos;
	}

	public MaterialMoradia getTipo_de_moradia() {
		return tipo_de_moradia;
	}

	public CondicaoLocal getCondicao_do_local() {
		return condicao_do_local;
	}

	public String getTrabalho_pai_visita() {
		return trabalho_pai_visita;
	}

	public String getTrabalho_mae_visita() {
		return trabalho_mae_visita;
	}

	public String getA_crianca_ja_teve_alguma_doenca_grave() {
		return a_crianca_ja_teve_alguma_doenca_grave;
	}

	public String getTem_alergia() {
		return tem_alergia;
	}

	public String getCarteira_vacinacao() {
		return carteira_vacinacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setNome_da_crianca(String nome_da_crianca) {
		this.nome_da_crianca = nome_da_crianca;
	}

	public void setData_de_nascimento(LocalDate data_de_nascimento) {
		this.data_de_nascimento = data_de_nascimento;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public void setIdade_mae(Integer idade_mae) {
		this.idade_mae = idade_mae;
	}

	public void setTrabalho_mae(String trabalho_mae) {
		this.trabalho_mae = trabalho_mae;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public void setIdade_pai(Integer idade_pai) {
		this.idade_pai = idade_pai;
	}

	public void setTrabalho_pai(String trabalho_pai) {
		this.trabalho_pai = trabalho_pai;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setPonto_de_referência(String ponto_de_referência) {
		this.ponto_de_referência = ponto_de_referência;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setObjetivo_da_matrícula(String objetivo_da_matrícula) {
		this.objetivo_da_matrícula = objetivo_da_matrícula;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public void setCoordenadora(String coordenadora) {
		this.coordenadora = coordenadora;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setVisitado(Boolean visitado) {
		this.visitado = visitado;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public void setQuantidade_de_comodos(Integer quantidade_de_comodos) {
		this.quantidade_de_comodos = quantidade_de_comodos;
	}

	public void setTipo_de_moradia(MaterialMoradia tipo_de_moradia) {
		this.tipo_de_moradia = tipo_de_moradia;
	}

	public void setCondicao_do_local(CondicaoLocal condicao_do_local) {
		this.condicao_do_local = condicao_do_local;
	}

	public void setTrabalho_pai_visita(String trabalho_pai_visita) {
		this.trabalho_pai_visita = trabalho_pai_visita;
	}

	public void setTrabalho_mae_visita(String trabalho_mae_visita) {
		this.trabalho_mae_visita = trabalho_mae_visita;
	}

	public void setA_crianca_ja_teve_alguma_doenca_grave(String a_crianca_ja_teve_alguma_doenca_grave) {
		this.a_crianca_ja_teve_alguma_doenca_grave = a_crianca_ja_teve_alguma_doenca_grave;
	}

	public void setTem_alergia(String tem_alergia) {
		this.tem_alergia = tem_alergia;
	}

	public void setCarteira_vacinacao(String carteira_vacinacao) {
		this.carteira_vacinacao = carteira_vacinacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Aluno inscreverAluno(){
		Aluno inscrito = new Aluno();
		
		inscrito.setNome(nome_da_crianca);
		inscrito.setData_de_nascimento(data_de_nascimento);
		inscrito.setSexo(sexo);
		inscrito.setMae(mae);
		inscrito.setProfissao_mae(trabalho_mae);
		inscrito.setPai(pai);
		inscrito.setProfissao_pai(trabalho_pai);
		inscrito.setEndereco_responsavel(endereco);
		inscrito.setTelefone_responsavel(telefone);
		inscrito.setResponsavel(responsavel);
		inscrito.setData_de_preenchimento(LocalDate.now());
		
		return inscrito;
	}

	public Integer getMid(){
		return this.mid;
	}
	
	public void setMid(Integer id){
		this.mid = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return this.nome_da_crianca;
	}
}
