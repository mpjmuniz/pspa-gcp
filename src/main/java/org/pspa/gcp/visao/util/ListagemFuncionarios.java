package org.pspa.gcp.visao.util;

import org.pspa.gcp.modelo.Funcionario;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.repositorios.RepositorioFuncionario;
import org.pspa.gcp.visao.Apresentador;
import org.springframework.context.ApplicationContext;

import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListagemFuncionarios extends VBox{
	/** ajuda à ligar janela da seleção à janela principal*/
	Apresentador apresentador;
	
	/** Botão para impressão da listagem*/
	private Button bImprimir;
	
	/** Repositório Spring, de onde são tirados os funcionarios*/
	private RepositorioFuncionario repositorio;
	
	/** palco onde residirá o nó*/
	protected Stage sSobreposto;
	
	/** Tabela que armazena funcionarios */
	private ListView<Funcionario> lvInterna;
	
	public ListagemFuncionarios(ApplicationContext contexto, Turma turma){
		super();
		
		this.apresentador = Apresentador.obterInstancia();
		
		this.repositorio = contexto.getBean(RepositorioFuncionario.class);
		
		this.lvInterna = new ListView<Funcionario>();
		this.lvInterna.getItems().addAll(repositorio.findFuncionariosByTurma(turma));
		
		this.lvInterna.setCellFactory(new Callback<ListView<Funcionario>, ListCell<Funcionario>>(){

			@Override
			public ListCell<Funcionario> call(ListView<Funcionario> param) {
				return new ListCell<Funcionario>() {
					@Override
					public void updateItem(Funcionario item, boolean empty) {
						// Must call super
						super.updateItem(item, empty);

						String name = null;

						// Format name 
						if (item == null || empty) {
							// No action to perform
						} else {
							name = item.getNome();
						}
						
						this.setText(name);
						setGraphic(null);
					}
				}; 
			}
			
		});
		
		lvInterna.setEditable(false);
		
		this.getChildren().add(lvInterna);
		
		this.bImprimir = new Button("Imprimir");
		this.bImprimir.setOnAction(e -> imprimir());
		this.getChildren().add(bImprimir);
		
		this.setSpacing(5);
		
		Scene cenaSobreposta = new Scene(this);
		sSobreposto.setScene(cenaSobreposta);
		sSobreposto.show();
	}

	private void imprimir() {
		PrinterJob job = PrinterJob.createPrinterJob();
		
		if (job.showPageSetupDialog(apresentador.obterPalco())) {
			
			if (job.showPrintDialog(apresentador.obterPalco())) {
				
				apresentador.cadastrarAjuda(job.jobStatusProperty());
				
				if (job.printPage(lvInterna)) {
					job.endJob();
				}
				
				apresentador.descadastrarAjuda();
				
			}
		}
		
		sSobreposto.close();
	}

}
