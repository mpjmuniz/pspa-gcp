package org.pspa.gcp.visao.dma;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import org.hibernate.Hibernate;
import org.pspa.gcp.modelo.AtividadeDiaria;
import org.pspa.gcp.modelo.AtividadeMensal;
import org.pspa.gcp.modelo.AtividadeSemanal;
import org.pspa.gcp.modelo.ConteudoDidatico;
import org.pspa.gcp.modelo.repositorios.RepositorioAtividadeDiaria;
import org.pspa.gcp.modelo.repositorios.RepositorioAtividadeMensal;
import org.pspa.gcp.modelo.repositorios.RepositorioAtividadeSemanal;
import org.pspa.gcp.modelo.repositorios.RepositorioConteudoDidatico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * TODO: refazer lógica de organização das atividades mensais, diárias e semanais
 * 
 * */

@Service
public class ServicoDistribuicaoMensalAtividades {
	
	@Autowired
	private RepositorioConteudoDidatico repositorioCD;
	
	@Autowired
	private RepositorioAtividadeDiaria repositorioAD;
	
	@Autowired
	private RepositorioAtividadeSemanal repositorioAS;
	
	@Autowired
	private RepositorioAtividadeMensal repositorioAM;

	@Autowired
	public ServicoDistribuicaoMensalAtividades(RepositorioConteudoDidatico repositorioCD,
												RepositorioAtividadeDiaria repositorioAD, 
												RepositorioAtividadeSemanal repositorioAS,
												RepositorioAtividadeMensal repositorioAM) {
		super();
		this.repositorioCD = repositorioCD;
		this.repositorioAD = repositorioAD;
		this.repositorioAS = repositorioAS;
		this.repositorioAM = repositorioAM;
	}

	@Transactional
	public AtividadeSemanal obterSemanaAtual() {
		
		AtividadeSemanal semana;
		LocalDate agora = LocalDate.now(),
				  segunda = obterSegunda(agora),
				  diaPrimeiro = obterDiaPrimeiro(agora);
		AtividadeMensal mes;
		AtividadeDiaria dia;
		
		// 1ª tentativa: semana existe, estamos no meio dela
		semana = repositorioAS.findOne(segunda);
		
		// 2ª tentativa: semana começou agora, ainda não existe, mas o mes existe
		if(semana == null){
			
			mes = repositorioAM.findOne(diaPrimeiro);
			
			/* 3ª tentativa: começo da semana e do mês
			if(mes == null){
				mes = repositorioAM.save(new AtividadeMensal(diaPrimeiro, ""));
			}*/
			
			semana = new AtividadeSemanal(segunda);
			
			for(LocalDate diaAtual = segunda; diaAtual.isBefore(segunda.plusDays(5)); diaAtual = diaAtual.plusDays(1)){
				
				dia = repositorioAD.save(new AtividadeDiaria(diaAtual, new ArrayList<>()));
				
				semana.getDias().add(dia);
			}
			
			semana = repositorioAS.save(semana);
			
			mes.getSemanas().add(semana);
			
			mes = repositorioAM.save(mes);
		}
		
		return semana;
	}

	public AtividadeSemanal salvarAtividadeSemanal(AtividadeSemanal atual) {
		return repositorioAS.save(atual);
	}

	public AtividadeMensal salvarAtividadeMensal(AtividadeMensal mes) {
		return repositorioAM.save(mes);
	}

	public AtividadeSemanal salvarConteudo(AtividadeSemanal atual, AtividadeDiaria ad, ConteudoDidatico p) {
		ConteudoDidatico q = repositorioCD.save(p);
		
		ad.getConteudos().add(q);
		repositorioAD.save(ad);
		
		return repositorioAS.save(atual);
	}
	
	public ConteudoDidatico salvarConteudo(ConteudoDidatico cd){
		return repositorioCD.save(cd);
	}
	
	public LocalDate obterSegunda(LocalDate data){
		
		LocalDate dataAtual = data;
		
		if(!dataAtual.getDayOfWeek().equals(DayOfWeek.MONDAY)){
			while(!dataAtual.getDayOfWeek().equals(DayOfWeek.MONDAY)){
				dataAtual = dataAtual.minusDays(1);
			}
		}
		
		return dataAtual;
	}
	
	public LocalDate obterDiaPrimeiro(LocalDate data){
		return LocalDate.of(data.getYear(), data.getMonthValue(), 1);
	}
	
	public AtividadeSemanal obterAtividadeSemanal(LocalDate segunda){
		
		return repositorioAS.findOne(segunda);
		
	}

	public void removerConteudo(AtividadeDiaria atividadeDiaria, ConteudoDidatico p) {
		
		atividadeDiaria.getConteudos().remove(p);
		repositorioAD.save(atividadeDiaria);
		repositorioCD.delete(p);
		
	}

	public AtividadeMensal obterMesAtual() {
		
		LocalDate agora = LocalDate.now(),
				  diaPrimeiro = obterDiaPrimeiro(agora);
		
		AtividadeMensal mes;
		
		mes = repositorioAM.findOne(diaPrimeiro);
		
		// come�o do m�s
		if(mes == null){
			mes = repositorioAM.save(new AtividadeMensal(diaPrimeiro, ""));
		}
		
		return mes;
	}
}
