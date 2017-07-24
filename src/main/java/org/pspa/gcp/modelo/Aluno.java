package org.pspa.gcp.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.pspa.gcp.modelo.enums.Instrucao;
import org.pspa.gcp.modelo.enums.Sexo;
import org.pspa.gcp.modelo.enums.UF;

@Entity
/** @author Marcelo Muniz
 * 
 * Versão 0.1: pronto para entrega
 * */
public class Aluno implements Participante{

	// Sobre o aluno
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;
	
	private String nome;

	private String ano_letivo;

	private Integer ID;

	private String CFC, BF, designação, NIS;

	private Sexo sexo;

	private LocalDate data_de_nascimento;
	private String nacionalidade;

	private UF UF;
	private String religião;
	private Boolean frequentou_EI;

	// Sobre o pai
	private String pai, RG_pai;
	private Boolean pai_falecido;

	private Instrucao pai_instrução;
	private Boolean pai_mora_com_aluno;
	private String profissão_pai;

	// Sobre a mãe
	private String mãe, NIS_mãe, RG_mãe;
	private Boolean mae_falecida;

	private Instrucao mãe_instrução;
	private Boolean mãe_mora_com_aluno;
	private String profissão_mãe;

	// Sobre o responsável
	private String responsável, tipo_responsável, endereço_responsável, CEP_responsável, telefone_responsável;

	// Contatos
	private String contato_1, telefone_contato_1, contato_2, telefone_contato_2;

	private String problemas_de_saude, educação_especial, atendimento_especial, frequentou_outra_instituição,
			meio_de_transporte, tempo_de_deslocamento;

	// Sobre o preenchimento
	private LocalDate data_de_preenchimento;

	// Responsável
	private String responsável_pelo_preenchimento;

	@ManyToOne
	@JoinColumn(name = "turma_id")
	private Turma turma;

	public Aluno(Integer mid, String nome, String ano_letivo, Integer iD, String cFC, String bF, String designação,
			String nIS, Sexo sexo, LocalDate data_de_nascimento, String nacionalidade, org.pspa.gcp.modelo.enums.UF uF,
			String religião, Boolean frequentou_EI, String pai, String rG_pai, Boolean pai_falecido,
			Instrucao pai_instrução, Boolean pai_mora_com_aluno, String profissão_pai, String mãe, String nIS_mãe,
			String rG_mãe, Boolean mae_falecida, Instrucao mãe_instrução, Boolean mãe_mora_com_aluno,
			String profissão_mãe, String responsável, String tipo_responsável, String endereço_responsável,
			String cEP_responsável, String telefone_responsável, String contato_1, String telefone_contato_1,
			String contato_2, String telefone_contato_2, String problemas_de_saude, String educação_especial,
			String atendimento_especial, String frequentou_outra_instituição, String meio_de_transporte,
			String tempo_de_deslocamento, LocalDate data_de_preenchimento, String responsável_pelo_preenchimento,
			Turma turma) {
		super();
		this.mid = mid;
		this.nome = nome;

		this.ano_letivo = ano_letivo;
		ID = iD;
		CFC = cFC;
		BF = bF;
		this.designação = designação;
		NIS = nIS;
		this.sexo = sexo;
		this.data_de_nascimento = data_de_nascimento;
		this.nacionalidade = nacionalidade;
		UF = uF;
		this.religião = religião;
		this.frequentou_EI = frequentou_EI;
		this.pai = pai;
		RG_pai = rG_pai;
		this.pai_falecido = pai_falecido;
		this.pai_instrução = pai_instrução;
		this.pai_mora_com_aluno = pai_mora_com_aluno;
		this.profissão_pai = profissão_pai;
		this.mãe = mãe;
		NIS_mãe = nIS_mãe;
		RG_mãe = rG_mãe;
		this.mae_falecida = mae_falecida;
		this.mãe_instrução = mãe_instrução;
		this.mãe_mora_com_aluno = mãe_mora_com_aluno;
		this.profissão_mãe = profissão_mãe;
		this.responsável = responsável;
		this.tipo_responsável = tipo_responsável;
		this.endereço_responsável = endereço_responsável;
		CEP_responsável = cEP_responsável;
		this.telefone_responsável = telefone_responsável;
		this.contato_1 = contato_1;
		this.telefone_contato_1 = telefone_contato_1;
		this.contato_2 = contato_2;
		this.telefone_contato_2 = telefone_contato_2;
		this.problemas_de_saude = problemas_de_saude;
		this.educação_especial = educação_especial;
		this.atendimento_especial = atendimento_especial;
		this.frequentou_outra_instituição = frequentou_outra_instituição;
		this.meio_de_transporte = meio_de_transporte;
		this.tempo_de_deslocamento = tempo_de_deslocamento;
		this.data_de_preenchimento = data_de_preenchimento;
		this.responsável_pelo_preenchimento = responsável_pelo_preenchimento;
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

	public String getDesignação() {
		return designação;
	}

	public void setDesignação(String designação) {
		this.designação = designação;
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

	public String getReligião() {
		return religião;
	}

	public void setReligião(String religião) {
		this.religião = religião;
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

	public Instrucao getPai_instrução() {
		return pai_instrução;
	}

	public void setPai_instrução(Instrucao pai_instrução) {
		this.pai_instrução = pai_instrução;
	}

	public Boolean getPai_mora_com_aluno() {
		return pai_mora_com_aluno;
	}

	public void setPai_mora_com_aluno(Boolean pai_mora_com_aluno) {
		this.pai_mora_com_aluno = pai_mora_com_aluno;
	}

	public String getProfissão_pai() {
		return profissão_pai;
	}

	public void setProfissão_pai(String profissão_pai) {
		this.profissão_pai = profissão_pai;
	}

	public String getMãe() {
		return mãe;
	}

	public void setMãe(String mãe) {
		this.mãe = mãe;
	}

	public String getNIS_mãe() {
		return NIS_mãe;
	}

	public void setNIS_mãe(String nIS_mãe) {
		NIS_mãe = nIS_mãe;
	}

	public String getRG_mãe() {
		return RG_mãe;
	}

	public void setRG_mãe(String rG_mãe) {
		RG_mãe = rG_mãe;
	}

	public Boolean getMae_falecida() {
		return mae_falecida;
	}

	public void setMae_falecida(Boolean mae_falecida) {
		this.mae_falecida = mae_falecida;
	}

	public Instrucao getMãe_instrução() {
		return mãe_instrução;
	}

	public void setMãe_instrução(Instrucao mãe_instrução) {
		this.mãe_instrução = mãe_instrução;
	}

	public Boolean getMãe_mora_com_aluno() {
		return mãe_mora_com_aluno;
	}

	public void setMãe_mora_com_aluno(Boolean mãe_mora_com_aluno) {
		this.mãe_mora_com_aluno = mãe_mora_com_aluno;
	}

	public String getProfissão_mãe() {
		return profissão_mãe;
	}

	public void setProfissão_mãe(String profissão_mãe) {
		this.profissão_mãe = profissão_mãe;
	}

	public String getResponsável() {
		return responsável;
	}

	public void setResponsável(String responsável) {
		this.responsável = responsável;
	}

	public String getTipo_responsável() {
		return tipo_responsável;
	}

	public void setTipo_responsável(String tipo_responsável) {
		this.tipo_responsável = tipo_responsável;
	}

	public String getEndereço_responsável() {
		return endereço_responsável;
	}

	public void setEndereço_responsável(String endereço_responsável) {
		this.endereço_responsável = endereço_responsável;
	}

	public String getCEP_responsável() {
		return CEP_responsável;
	}

	public void setCEP_responsável(String cEP_responsável) {
		CEP_responsável = cEP_responsável;
	}

	public String getTelefone_responsável() {
		return telefone_responsável;
	}

	public void setTelefone_responsável(String telefone_responsável) {
		this.telefone_responsável = telefone_responsável;
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

	public String getEducação_especial() {
		return educação_especial;
	}

	public void setEducação_especial(String educação_especial) {
		this.educação_especial = educação_especial;
	}

	public String getAtendimento_especial() {
		return atendimento_especial;
	}

	public void setAtendimento_especial(String atendimento_especial) {
		this.atendimento_especial = atendimento_especial;
	}

	public String getFrequentou_outra_instituição() {
		return frequentou_outra_instituição;
	}

	public void setFrequentou_outra_instituição(String frequentou_outra_instituição) {
		this.frequentou_outra_instituição = frequentou_outra_instituição;
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

	public String getResponsável_pelo_preenchimento() {
		return responsável_pelo_preenchimento;
	}

	public void setResponsável_pelo_preenchimento(String responsável_pelo_preenchimento) {
		this.responsável_pelo_preenchimento = responsável_pelo_preenchimento;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
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
}
