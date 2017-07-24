package org.pspa.gcp.visao;

import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioTurma;
import org.pspa.gcp.visao.util.ListagemAlunos;
import org.pspa.gcp.visao.util.ListagemAlunosNascimento;
import org.pspa.gcp.visao.util.ListagemFuncionarios;
import org.springframework.context.ApplicationContext;

import javafx.scene.control.Button;

public class VisaoTurmas extends VisaoCadastros<Turma>{

	RepositorioTurma repositorio;
	
	ApplicationContext contexto;
	
	Button bListarAlunos;
	
	Button bListarAlunosComNascimento;
	
	Button bListarFuncionarios;
	
	protected VisaoTurmas(ApplicationContext contexto) {
		super(Turma.class);
		
		repositorio = contexto.getBean(RepositorioTurma.class);
		
		this.contexto = contexto;
		
		bListarAlunos = new Button("Listar Alunos");
		bListarAlunos.setOnAction(e -> listarAlunos());
		bListarAlunos.setPrefWidth(1000);
		
		bListarAlunosComNascimento = new Button("Listar Aniversários de Alunos");
		bListarAlunosComNascimento.setOnAction(e -> imprimirAlunos());
		bListarAlunosComNascimento.setPrefWidth(1000);
		
		bListarAlunos = new Button("Listar Funcionarios");
		bListarAlunos.setOnAction(e -> listarFuncionarios());
		bListarAlunos.setPrefWidth(1000);
		
		vbListagem.getChildren().add(bListarAlunos);
		
		popularVisaoInicialmente();
	}
	
	private void listarFuncionarios() {
		if (lElementos.getSelectionModel().isEmpty()) {
			ajuda.set("Selecione a turma que terá os funcionários listados.");
			return;
		}
		
		@SuppressWarnings("unused")
		ListagemFuncionarios list = new ListagemFuncionarios(contexto, lElementos.getSelectionModel().getSelectedItem());
	}

	private void listarAlunos() {
		if (lElementos.getSelectionModel().isEmpty()) {
			ajuda.set("Selecione a turma que terá os funcionários listados.");
			return;
		}
		
		@SuppressWarnings("unused")
		ListagemAlunos list = new ListagemAlunos(contexto, lElementos.getSelectionModel().getSelectedItem());
	}

	@Override
	public void popularVisaoInicialmente(){
		this.lElementos.getItems().addAll(repositorio.findAll());
	}

	@Override
	public Turma persistir(Turma objeto) {
		return repositorio.save(objeto);
	}

	@Override
	public void deletar(Turma objeto) {
		repositorio.delete(objeto);
	}
	
	public void imprimirAlunos(){
		if (lElementos.getSelectionModel().isEmpty()) {
			ajuda.set("Selecione a turma que terá os alunos listados.");
			return;
		}
		
		@SuppressWarnings("unused")
		ListagemAlunosNascimento list = new ListagemAlunosNascimento(contexto, lElementos.getSelectionModel().getSelectedItem());
	}
}
