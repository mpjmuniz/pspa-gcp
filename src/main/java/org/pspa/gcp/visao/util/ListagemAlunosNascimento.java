package org.pspa.gcp.visao.util;

import java.time.LocalDate;

import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioAluno;
import org.pspa.gcp.visao.Apresentador;
import org.springframework.context.ApplicationContext;

import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListagemAlunosNascimento extends VBox{
	
	/** ajuda à ligar janela da seleção à janela principal*/
	Apresentador apresentador;
	
	/** Botão para impressão da listagem*/
	private Button bImprimir;
	
	/** Repositório Spring, de onde são tirados os alunos*/
	private RepositorioAluno repositorio;
	
	/** palco onde residirá o nó*/
	protected Stage sSobreposto;
	
	/** Tabela que armazena alunos e datas de nascimento*/
	private TableView<Aluno> tvInterna;
	
	@SuppressWarnings("unchecked")
	public ListagemAlunosNascimento(ApplicationContext contexto, Turma turma){
		super();
		
		this.apresentador = Apresentador.obterInstancia();
		
		this.repositorio = contexto.getBean(RepositorioAluno.class);
		
		this.tvInterna = new TableView<Aluno>();
		this.tvInterna.getItems().addAll(repositorio.findAlunosByTurma(turma));
		
		tvInterna.getColumns().forEach(e -> 
			e.setPrefWidth((apresentador.obterPalco().getWidth() / tvInterna.getColumns().size()) - 1));
		
		tvInterna.getColumns().addAll(obterColunaNome(),
									  obterColunaNascimento());
		
		tvInterna.setEditable(false);
		
		this.getChildren().add(tvInterna);
		
		this.bImprimir = new Button("Imprimir");
		this.bImprimir.setOnAction(e -> imprimir());
		this.getChildren().add(bImprimir);
		
		this.setSpacing(5);
		
		Scene cenaSobreposta = new Scene(this);
		sSobreposto.setScene(cenaSobreposta);
		sSobreposto.show();
	}

	private TableColumn<Aluno, LocalDate> obterColunaNascimento() {
		TableColumn<Aluno, LocalDate> colunaNascimento = new TableColumn<>("Data de Nascimento");
		colunaNascimento.setCellValueFactory(new PropertyValueFactory<>("data_de_nascimento"));		
		return colunaNascimento;
	}

	private TableColumn<Aluno, String> obterColunaNome() {
		TableColumn<Aluno, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));		
		return colunaNome;
	}

	private void imprimir() {
		PrinterJob job = PrinterJob.createPrinterJob();
		
		if (job.showPageSetupDialog(apresentador.obterPalco())) {
			
			if (job.showPrintDialog(apresentador.obterPalco())) {
				
				apresentador.cadastrarAjuda(job.jobStatusProperty());
				
				if (job.printPage(tvInterna)) {
					job.endJob();
				}
				
				apresentador.descadastrarAjuda();
				
			}
		}
		
		sSobreposto.close();
	}
	
	
}
