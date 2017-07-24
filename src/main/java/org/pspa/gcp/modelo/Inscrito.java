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

	
	private String nome_da_criança;
	private LocalDate data_de_nascimento;
	private Sexo sexo;

	private String mãe;
	private Integer idade_mãe;
	private String trabalho_mãe;

	private String pai;
	private Integer idade_pai;
	private String trabalho_pai;

	private String endereço;
	private String ponto_de_referência;

	private String telefone;
	private String objetivo_da_matrícula;

	private String responsável;
	private String coordenadora;

	private LocalDate data;

	private Boolean visitado;
	
	

	public Inscrito() {
		this("João", LocalDate.now(), Sexo.Masculino, "Joana", 20, "Faxineira", "José", 30, "Marceneiro", "Rua zó",
				"perto", "23112412", "porque sim", "pai", "Alguém", LocalDate.now(), false);
	}

	public Inscrito(String nome_da_criança, LocalDate data_de_nascimento, Sexo sexo, String mãe, Integer idade_mãe,
			String trabalho_mãe, String pai, Integer idade_pai, String trabalho_pai, String endereço,
			String ponto_de_referência, String telefone, String objetivo_da_matrícula, String responsável,
			String coordenadora, LocalDate data, Boolean visitado) {
		this.nome_da_criança = nome_da_criança;
		this.data_de_nascimento = data_de_nascimento;
		this.sexo = sexo;
		this.mãe = mãe;
		this.idade_mãe = idade_mãe;
		this.trabalho_mãe = trabalho_mãe;
		this.pai = pai;
		this.idade_pai = idade_pai;
		this.trabalho_pai = trabalho_pai;
		this.endereço = endereço;
		this.ponto_de_referência = ponto_de_referência;
		this.telefone = telefone;
		this.objetivo_da_matrícula = objetivo_da_matrícula;
		this.responsável = responsável;
		this.coordenadora = coordenadora;
		this.data = data;
		this.visitado = visitado;
	}

	// Sobre a visita domiciliar

	private Casa casa;
	private Integer quantidade_de_cômodos;
	private MaterialMoradia tipo_de_moradia;
	private CondicaoLocal condição_do_local;

	private String trabalho_pai_visita; // novamente?
	private String trabalho_mãe_visita;

	private String a_criança_já_teve_alguma_doença_grave;

	private String tem_alergia;
	private String carteira_vacinação;

	private String observação;

	public void visitar(Casa casa, Integer quantidade_de_cômodos, MaterialMoradia tipo_de_moradia,
			CondicaoLocal condição_do_local, String trabalho_pai_visita, String trabalho_mãe_visita,
			String a_criança_já_teve_alguma_doença_grave, String tem_alergia, String carteira_vacinação,
			String observação) {
		this.casa = casa;
		this.quantidade_de_cômodos = quantidade_de_cômodos;
		this.tipo_de_moradia = tipo_de_moradia;
		this.condição_do_local = condição_do_local;
		this.trabalho_pai_visita = trabalho_pai_visita;
		this.trabalho_mãe_visita = trabalho_mãe_visita;
		this.a_criança_já_teve_alguma_doença_grave = a_criança_já_teve_alguma_doença_grave;
		this.tem_alergia = tem_alergia;
		this.carteira_vacinação = carteira_vacinação;
		this.observação = observação;
	}

	public String getNome_da_criança() {
		return nome_da_criança;
	}

	public LocalDate getData_de_nascimento() {
		return data_de_nascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public String getMãe() {
		return mãe;
	}

	public Integer getIdade_mãe() {
		return idade_mãe;
	}

	public String getTrabalho_mãe() {
		return trabalho_mãe;
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

	public String getEndereço() {
		return endereço;
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

	public String getResponsável() {
		return responsável;
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

	public Integer getQuantidade_de_cômodos() {
		return quantidade_de_cômodos;
	}

	public MaterialMoradia getTipo_de_moradia() {
		return tipo_de_moradia;
	}

	public CondicaoLocal getCondição_do_local() {
		return condição_do_local;
	}

	public String getTrabalho_pai_visita() {
		return trabalho_pai_visita;
	}

	public String getTrabalho_mãe_visita() {
		return trabalho_mãe_visita;
	}

	public String getA_criança_já_teve_alguma_doença_grave() {
		return a_criança_já_teve_alguma_doença_grave;
	}

	public String getTem_alergia() {
		return tem_alergia;
	}

	public String getCarteira_vacinação() {
		return carteira_vacinação;
	}

	public String getObservação() {
		return observação;
	}

	public void setNome_da_criança(String nome_da_criança) {
		this.nome_da_criança = nome_da_criança;
	}

	public void setData_de_nascimento(LocalDate data_de_nascimento) {
		this.data_de_nascimento = data_de_nascimento;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setMãe(String mãe) {
		this.mãe = mãe;
	}

	public void setIdade_mãe(Integer idade_mãe) {
		this.idade_mãe = idade_mãe;
	}

	public void setTrabalho_mãe(String trabalho_mãe) {
		this.trabalho_mãe = trabalho_mãe;
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

	public void setEndereço(String endereço) {
		this.endereço = endereço;
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

	public void setResponsável(String responsável) {
		this.responsável = responsável;
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

	public void setQuantidade_de_cômodos(Integer quantidade_de_cômodos) {
		this.quantidade_de_cômodos = quantidade_de_cômodos;
	}

	public void setTipo_de_moradia(MaterialMoradia tipo_de_moradia) {
		this.tipo_de_moradia = tipo_de_moradia;
	}

	public void setCondição_do_local(CondicaoLocal condição_do_local) {
		this.condição_do_local = condição_do_local;
	}

	public void setTrabalho_pai_visita(String trabalho_pai_visita) {
		this.trabalho_pai_visita = trabalho_pai_visita;
	}

	public void setTrabalho_mãe_visita(String trabalho_mãe_visita) {
		this.trabalho_mãe_visita = trabalho_mãe_visita;
	}

	public void setA_criança_já_teve_alguma_doença_grave(String a_criança_já_teve_alguma_doença_grave) {
		this.a_criança_já_teve_alguma_doença_grave = a_criança_já_teve_alguma_doença_grave;
	}

	public void setTem_alergia(String tem_alergia) {
		this.tem_alergia = tem_alergia;
	}

	public void setCarteira_vacinação(String carteira_vacinação) {
		this.carteira_vacinação = carteira_vacinação;
	}

	public void setObservação(String observação) {
		this.observação = observação;
	}
	
	public Aluno inscreverAluno(){
		Aluno inscrito = new Aluno();
		
		inscrito.setNome(nome_da_criança);
		inscrito.setData_de_nascimento(data_de_nascimento);
		inscrito.setSexo(sexo);
		inscrito.setMãe(mãe);
		inscrito.setProfissão_mãe(trabalho_mãe);
		inscrito.setPai(pai);
		inscrito.setProfissão_pai(trabalho_pai);
		inscrito.setEndereço_responsável(endereço);
		inscrito.setTelefone_responsável(telefone);
		inscrito.setResponsável(responsável);
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
		return this.nome_da_criança;
	}
}
