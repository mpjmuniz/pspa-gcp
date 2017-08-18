package org.pspa.gcp.modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.pspa.gcp.modelo.enums.Instrucao;
import org.pspa.gcp.modelo.enums.Sexo;
import org.pspa.gcp.modelo.enums.UF;


/** @author Marcelo Muniz
 * 
 * 	Entidade que representa um aluno particular da instituicao
 * 
 * Versao 0.1: pronto para entrega
 * */
@Entity
public class Aluno implements Participante{

	// Sobre o aluno
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;
	
	private String nome;

	private String ano_letivo;

	private Integer ID;

	private String CFC, BF, designacao, NIS;

	private Sexo sexo;

	private LocalDate data_de_nascimento;
	private String nacionalidade;

	private UF UF;
	private String religiao;
	private Boolean frequentou_EI;

	// Sobre o pai
	private String pai, RG_pai;
	private Boolean pai_falecido;

	private Instrucao pai_instrucao;
	private Boolean pai_mora_com_aluno;
	private String profissao_pai;

	// Sobre a mae
	private String mae, NIS_mae, RG_mae;
	private Boolean mae_falecida;

	private Instrucao mae_instrucao;
	private Boolean mae_mora_com_aluno;
	private String profissao_mae;

	// Sobre o responsavel
	private String responsavel, tipo_responsavel, endereco_responsavel, CEP_responsavel, telefone_responsavel;

	// Contatos
	private String contato_1, telefone_contato_1, contato_2, telefone_contato_2;

private String problemas_de_saude, educacao_especial, atendimento_especial, frequentou_outra_instituicao,
		meio_de_transporte, tempo_de_deslocamento;

// Sobre o preenchimento
private LocalDate data_de_preenchimento;

// Responsavel
private String responsavel_pelo_preenchimento;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "turma_id")
private Turma turma;

public Aluno(Integer mid, String nome, String ano_letivo, Integer iD, String cFC, String bF, String designacao,
		String nIS, Sexo sexo, LocalDate data_de_nascimento, String nacionalidade, org.pspa.gcp.modelo.enums.UF uF,
		String religiao, Boolean frequentou_EI, String pai, String rG_pai, Boolean pai_falecido,
		Instrucao pai_instrucao, Boolean pai_mora_com_aluno, String profissao_pai, String mae, String nIS_mae,
		String rG_mae, Boolean mae_falecida, Instrucao mae_instrucao, Boolean mae_mora_com_aluno,
		String profissao_mae, String responsavel, String tipo_responsavel, String endereco_responsavel,
			String cEP_responsavel, String telefone_responsavel, String contato_1, String telefone_contato_1,
			String contato_2, String telefone_contato_2, String problemas_de_saude, String educacao_especial,
			String atendimento_especial, String frequentou_outra_instituicao, String meio_de_transporte,
			String tempo_de_deslocamento, LocalDate data_de_preenchimento, String responsavel_pelo_preenchimento,
			Turma turma) {
		super();
		this.mid = mid;
		this.nome = nome;

		this.ano_letivo = ano_letivo;
		ID = iD;
		CFC = cFC;
		BF = bF;
		this.designacao = designacao;
		NIS = nIS;
		this.sexo = sexo;
		this.data_de_nascimento = data_de_nascimento;
		this.nacionalidade = nacionalidade;
		UF = uF;
		this.religiao = religiao;
		this.frequentou_EI = frequentou_EI;
		this.pai = pai;
		RG_pai = rG_pai;
		this.pai_falecido = pai_falecido;
		this.pai_instrucao = pai_instrucao;
		this.pai_mora_com_aluno = pai_mora_com_aluno;
		this.profissao_pai = profissao_pai;
		this.mae = mae;
		NIS_mae = nIS_mae;
		RG_mae = rG_mae;
		this.mae_falecida = mae_falecida;
		this.mae_instrucao = mae_instrucao;
		this.mae_mora_com_aluno = mae_mora_com_aluno;
		this.profissao_mae = profissao_mae;
		this.responsavel = responsavel;
		this.tipo_responsavel = tipo_responsavel;
		this.endereco_responsavel = endereco_responsavel;
		CEP_responsavel = cEP_responsavel;
		this.telefone_responsavel = telefone_responsavel;
		this.contato_1 = contato_1;
		this.telefone_contato_1 = telefone_contato_1;
		this.contato_2 = contato_2;
		this.telefone_contato_2 = telefone_contato_2;
		this.problemas_de_saude = problemas_de_saude;
		this.educacao_especial = educacao_especial;
		this.atendimento_especial = atendimento_especial;
		this.frequentou_outra_instituicao = frequentou_outra_instituicao;
		this.meio_de_transporte = meio_de_transporte;
		this.tempo_de_deslocamento = tempo_de_deslocamento;
		this.data_de_preenchimento = data_de_preenchimento;
		this.responsavel_pelo_preenchimento = responsavel_pelo_preenchimento;
		this.turma = turma;
	}

	public Aluno() {
		this(1, "", "", 1, "", "", "", "", Sexo.Masculino, LocalDate.now(), "", 
			 org.pspa.gcp.modelo.enums.UF.RJ, "", false, "", "", false, 
			 Instrucao.Fundamental_Completo, false, "", "", "", "", false, 
			 Instrucao.Fundamental_Completo, false, "", "", "", "", "", "", "", 
			 "", "", "", "", "", "", "", "", "", LocalDate.now(), "", 
			 null);
	}
	
	public Aluno(String nome) {
		this(1, nome, "", 1, "", "", "", "", Sexo.Masculino, LocalDate.now(), "", 
			 org.pspa.gcp.modelo.enums.UF.RJ, "", false, "", "", false, 
			 Instrucao.Fundamental_Completo, false, "", "", "", "", false, 
			 Instrucao.Fundamental_Completo, false, "", "", "", "", "", "", "", 
			 "", "", "", "", "", "", "", "", "", LocalDate.now(), "", 
			 null);
	}

	public String getAno_letivo() {
		return ano_letivo;
	}

	public void setAno_letivo(String ano_letivo) {
		this.ano_letivo = ano_letivo;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getCFC() {
		return CFC;
	}

	public void setCFC(String cFC) {
		CFC = cFC;
	}

	public String getBF() {
		return BF;
	}

	public void setBF(String bF) {
		BF = bF;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getNIS() {
		return NIS;
	}

	public void setNIS(String nIS) {
		NIS = nIS;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public LocalDate getData_de_nascimento() {
		return data_de_nascimento;
	}

	public void setData_de_nascimento(LocalDate data_de_nascimento) {
		this.data_de_nascimento = data_de_nascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public UF getUF() {
		return UF;
	}

	public void setUF(UF uF) {
		UF = uF;
	}

	public String getReligiao() {
		return religiao;
	}

	public void setReligiao(String religiao) {
		this.religiao = religiao;
	}

	public Boolean getFrequentou_EI() {
		return frequentou_EI;
	}

	public void setFrequentou_EI(Boolean frequentou_EI) {
		this.frequentou_EI = frequentou_EI;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getRG_pai() {
		return RG_pai;
	}

	public void setRG_pai(String rG_pai) {
		RG_pai = rG_pai;
	}

	public Boolean getPai_falecido() {
		return pai_falecido;
	}

	public void setPai_falecido(Boolean pai_falecido) {
		this.pai_falecido = pai_falecido;
	}

	public Instrucao getPai_instrucao() {
		return pai_instrucao;
	}

	public void setPai_instrucao(Instrucao pai_instrucao) {
		this.pai_instrucao = pai_instrucao;
	}

	public Boolean getPai_mora_com_aluno() {
		return pai_mora_com_aluno;
	}

	public void setPai_mora_com_aluno(Boolean pai_mora_com_aluno) {
		this.pai_mora_com_aluno = pai_mora_com_aluno;
	}

	public String getProfissao_pai() {
		return profissao_pai;
	}

	public void setProfissao_pai(String profissao_pai) {
		this.profissao_pai = profissao_pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getNIS_mae() {
		return NIS_mae;
	}

	public void setNIS_mae(String nIS_mae) {
		NIS_mae = nIS_mae;
	}

	public String getRG_mae() {
		return RG_mae;
	}

	public void setRG_mae(String rG_mae) {
		RG_mae = rG_mae;
	}

	public Boolean getMae_falecida() {
		return mae_falecida;
	}

	public void setMae_falecida(Boolean mae_falecida) {
		this.mae_falecida = mae_falecida;
	}

	public Instrucao getMae_instrucao() {
		return mae_instrucao;
	}

	public void setMae_instrucao(Instrucao mae_instrucao) {
		this.mae_instrucao = mae_instrucao;
	}

	public Boolean getMae_mora_com_aluno() {
		return mae_mora_com_aluno;
	}

	public void setMae_mora_com_aluno(Boolean mae_mora_com_aluno) {
		this.mae_mora_com_aluno = mae_mora_com_aluno;
	}

	public String getProfissao_mae() {
		return profissao_mae;
	}

	public void setProfissao_mae(String profissao_mae) {
		this.profissao_mae = profissao_mae;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getTipo_responsavel() {
		return tipo_responsavel;
	}

	public void setTipo_responsavel(String tipo_responsavel) {
		this.tipo_responsavel = tipo_responsavel;
	}

	public String getEndereco_responsavel() {
		return endereco_responsavel;
	}

	public void setEndereco_responsavel(String endereco_responsavel) {
		this.endereco_responsavel = endereco_responsavel;
	}

	public String getCEP_responsavel() {
		return CEP_responsavel;
	}

	public void setCEP_responsavel(String cEP_responsavel) {
		CEP_responsavel = cEP_responsavel;
	}

	public String getTelefone_responsavel() {
		return telefone_responsavel;
	}

	public void setTelefone_responsavel(String telefone_responsavel) {
		this.telefone_responsavel = telefone_responsavel;
	}

	public String getContato_1() {
		return contato_1;
	}

	public void setContato_1(String contato_1) {
		this.contato_1 = contato_1;
	}

	public String getTelefone_contato_1() {
		return telefone_contato_1;
	}

	public void setTelefone_contato_1(String telefone_contato_1) {
		this.telefone_contato_1 = telefone_contato_1;
	}

	public String getContato_2() {
		return contato_2;
	}

	public void setContato_2(String contato_2) {
		this.contato_2 = contato_2;
	}

	public String getTelefone_contato_2() {
		return telefone_contato_2;
	}

	public void setTelefone_contato_2(String telefone_contato_2) {
		this.telefone_contato_2 = telefone_contato_2;
	}

	public String getProblemas_de_saude() {
		return problemas_de_saude;
	}

	public void setProblemas_de_saude(String problemas_de_saude) {
		this.problemas_de_saude = problemas_de_saude;
	}

	public String getEducacao_especial() {
		return educacao_especial;
	}

	public void setEducacao_especial(String educacao_especial) {
		this.educacao_especial = educacao_especial;
	}

	public String getAtendimento_especial() {
		return atendimento_especial;
	}

	public void setAtendimento_especial(String atendimento_especial) {
		this.atendimento_especial = atendimento_especial;
	}

	public String getFrequentou_outra_instituicao() {
		return frequentou_outra_instituicao;
	}

	public void setFrequentou_outra_instituicao(String frequentou_outra_instituicao) {
		this.frequentou_outra_instituicao = frequentou_outra_instituicao;
	}

	public String getMeio_de_transporte() {
		return meio_de_transporte;
	}

	public void setMeio_de_transporte(String meio_de_transporte) {
		this.meio_de_transporte = meio_de_transporte;
	}

	public String getTempo_de_deslocamento() {
		return tempo_de_deslocamento;
	}

	public void setTempo_de_deslocamento(String tempo_de_deslocamento) {
		this.tempo_de_deslocamento = tempo_de_deslocamento;
	}

	public LocalDate getData_de_preenchimento() {
		return data_de_preenchimento;
	}

	public void setData_de_preenchimento(LocalDate data_de_preenchimento) {
		this.data_de_preenchimento = data_de_preenchimento;
	}

	public String getResponsavel_pelo_preenchimento() {
		return responsavel_pelo_preenchimento;
	}

	public void setResponsavel_pelo_preenchimento(String responsavel_pelo_preenchimento) {
		this.responsavel_pelo_preenchimento = responsavel_pelo_preenchimento;
	}

	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		if(turma != null) turma.getAlunos().add(this);
		this.turma = turma;
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
	
	public String toString(){
		return this.nome;
	}
	
	public boolean equals(Object other) {
		if(other == null) return false;
		
		return ((Aluno) other).getMid() == this.getMid();
	}
}
