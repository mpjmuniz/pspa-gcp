package org.pspa.gcp.visao.quadroestrutural;

import java.util.ArrayList;
import java.util.List;

import org.pspa.gcp.modelo.Funcionario;
import org.pspa.gcp.modelo.Turma;
import org.pspa.gcp.modelo.enums.Grupamento;
import org.pspa.gcp.visao.Apresentador;
import org.springframework.context.ApplicationContext;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

public class VisaoQuadroEstrutural extends VBox {
	
	private ServicoQuadroEstrutural servico;
	
	private final TableView<Turma> tabela;
	
	private Apresentador apresentador;
	
	private Button imprimir;
	
	private SimpleStringProperty ajuda;
	
	@SuppressWarnings("unchecked")
	public VisaoQuadroEstrutural(ApplicationContext contexto){
		
		servico = contexto.getBean(ServicoQuadroEstrutural.class);
		
		tabela = new TableView<>();
		
		apresentador = Apresentador.obterInstancia();
		
		tabela.getColumns().addAll(obterColunaGrupamento(),
									 obterColunaTurma(),
									 obterColunaAlunosMatriculados(),
									 obterColunaProfessor()
									);
		tabela.getColumns().addAll(obterColunasAuxiliares());
		
		tabela.getItems().addAll(servico.obterTurmas());
		
		tabela.setEditable(false);
		
		tabela.getColumns().forEach(e -> {
			e.setPrefWidth((apresentador.obterPalco().getWidth() / tabela.getColumns().size()) - 1);
			
		});
		
		ajuda = new SimpleStringProperty("");
		apresentador.cadastrarAjuda(ajuda);
		
		this.setSpacing(10);
		
		imprimir = new Button("Imprimir");
		imprimir.setOnAction(e -> imprimir());
		
		tabela.setPrefHeight(tabela.getMaxHeight());
		
		this.getChildren().addAll(imprimir, tabela);
		this.setPadding(new Insets(10));
	}

	private TableColumn<Turma, Grupamento> obterColunaGrupamento() {
		TableColumn<Turma, Grupamento> colunaGrupamento = new TableColumn<>("Grupamento");
		colunaGrupamento.setCellValueFactory(new PropertyValueFactory<>("grupamento"));
		
		// By default, all cells are have null values
		colunaGrupamento.setCellValueFactory(
				cellData -> {
					Grupamento g = cellData.getValue().getGrupamento();
					return new ReadOnlyObjectWrapper<Grupamento>(g);
				});
		
		// Set a ComboBoxTableCell, so we can selects a value from a list
		colunaGrupamento.setCellFactory(
			ComboBoxTableCell.<Turma, Grupamento>forTableColumn(Grupamento.values()));
		
		// Add an event handler to handle the edit commit event.
		// It displays the selected value on the standard output
		colunaGrupamento.setOnEditCommit(e -> {
			Turma t = e.getRowValue();
			t.setGrupamento(e.getNewValue());
			servico.salvarTurma(t);
		});
		
		return colunaGrupamento;

	}

	private List<TableColumn<Turma, Funcionario>> obterColunasAuxiliares() {
		
		List<TableColumn<Turma, Funcionario>> colunas = new ArrayList<>(); 
		
		TableColumn<Turma, Funcionario> coluna;
		
		List<Funcionario> inseridos = new ArrayList<>();
		
		for(int i = 0; i < 3; i++){
			
			coluna = new TableColumn<>("Auxiliar de Creche");
			
			coluna.setCellValueFactory(e -> {
				List<Funcionario> fs = e.getValue().getAuxiliares();
				
				for(Funcionario f : fs){
					if(!inseridos.contains(f)){
						inseridos.add(f);
						
						return new ReadOnlyObjectWrapper<Funcionario>(f);
					}
				}
				
				return null;
			});
			
			colunas.add(coluna);
		}
		
		return colunas;
	}

	private TableColumn<Turma, Funcionario> obterColunaProfessor() {
		TableColumn<Turma, Funcionario> coluna = new TableColumn<>("Professor");			 
			
		coluna.setCellValueFactory(e -> {
			Funcionario f = e.getValue().getProfessor();
			
			return new ReadOnlyObjectWrapper<Funcionario>(f);
		});
		
		return coluna;
	}

	private TableColumn<Turma, Number> obterColunaAlunosMatriculados() {
		TableColumn<Turma, Number> colunaAlunosMatriculados = new TableColumn<>("Alunos Matriculados");
		colunaAlunosMatriculados.setCellValueFactory(
				cellData -> {
					Turma t = cellData.getValue();
					ObservableValue<Number> roiw = new ReadOnlyIntegerWrapper(t.getAlunos().size());
					return roiw;					
				});
		return colunaAlunosMatriculados;
	}

	private TableColumn<Turma, String> obterColunaTurma() {
		TableColumn<Turma, String> colunaTurma = new TableColumn<>("Turma");
		colunaTurma.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaTurma.setCellFactory(TextFieldTableCell.<Turma>forTableColumn());
		colunaTurma.setOnEditCommit(e -> {
			Turma t = e.getRowValue();
			t.setNome(e.getNewValue());
			
			servico.salvarTurma(t);
		});
		
		return colunaTurma;
	}
	
	// TODO: testar num ambiente apropriado
	private void imprimir() {
		PrinterJob job = PrinterJob.createPrinterJob();
		
		if (job.showPageSetupDialog(apresentador.obterPalco())) {
			
			if (job.showPrintDialog(apresentador.obterPalco())) {
				
				ajuda.bind(job.jobStatusProperty().asString());
				
				if (job.printPage(tabela)) {
					job.endJob();
				}
				
				ajuda.unbind();
			}
		}
	}

	public void atualizar() {
		tabela.refresh();
	}

}
