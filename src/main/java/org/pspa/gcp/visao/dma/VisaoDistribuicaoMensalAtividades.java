package org.pspa.gcp.visao.dma;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Locale.LanguageRange;
import java.util.ResourceBundle;

import org.pspa.gcp.modelo.AtividadeDiaria;
import org.pspa.gcp.modelo.AtividadeMensal;
import org.pspa.gcp.modelo.AtividadeSemanal;
import org.pspa.gcp.modelo.ConteudoDidatico;
import org.pspa.gcp.visao.Apresentador;
import org.springframework.context.ApplicationContext;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class VisaoDistribuicaoMensalAtividades extends VBox{
	
	private AtividadeMensal atualMensal;
	
	private AtividadeSemanal atual;
	
	private ServicoDistribuicaoMensalAtividades servico;
	
	private Apresentador apresentador;
	
	private SimpleStringProperty ajuda;
	
	@FXML
	private Accordion aDias;
	
	@FXML
	private TextArea taAvaliacao;
	
	@FXML
	private TextField tfTemaMes;
	
	@FXML
	private ScrollPane spDias;
	
	@FXML
	private Button bSalvar,
				   bAdicionar,
				   bRemover;
	
	@FXML
	private DatePicker dpOutraSemana;
	
	/** campo usado pela biblioteca JavaFX */
	@FXML
	private URL location;

	/** campo usado pela biblioteca JavaFX*/
	@FXML
	private ResourceBundle resources;
	
	/**
	 * TODO: preparar um fluxo de uso da cena
	 * 
	 * */
	
	public VisaoDistribuicaoMensalAtividades(ApplicationContext contextoSpring) {
		apresentador = Apresentador.obterInstancia();
		
		servico = contextoSpring.getBean(ServicoDistribuicaoMensalAtividades.class);
		
		// Carregar o arquivo de definição da estrutura da tela, .fxml
		URL fxmlUrl = this.getClass().getClassLoader().getResource("org/pspa/gcp/visao/fxml/dma.fxml");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlUrl);
		
		// Definir organização lógica e controle da tela 
		loader.setRoot(this);
		loader.setController(this);
		
		// Carregar
		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		ajuda = new SimpleStringProperty("");
		apresentador.cadastrarAjuda(ajuda);
		
		atualMensal = servico.obterMesAtual();
		
		atual = servico.obterSemanaAtual();
		
		atualizar();
		
		spDias.setPrefWidth(apresentador.obterPalco().getWidth());
		spDias.setPrefHeight(apresentador.obterPalco().getHeight() / 2.3);
		aDias.setPrefWidth(apresentador.obterPalco().getWidth() - (this.getPadding().getLeft() + 2) * 2 );
		
		tfTemaMes.setPrefWidth(apresentador.obterPalco().getWidth() / 3);
		
		bSalvar.setOnAction(e -> salvar());
		bAdicionar.setOnAction(e -> adicionar());
		bRemover.setOnAction(e -> remover());
		
		
		Callback<DatePicker, DateCell> fabricaDias = 
		new Callback<DatePicker, DateCell>() {
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override 
					public void updateItem(LocalDate item, boolean empty) {
						// Must call super
						super.updateItem(item, empty);
						
						DayOfWeek day = DayOfWeek.from(item);
						if (day != DayOfWeek.MONDAY) {
							this.setDisable(true);
						}
					}
				};
			}};
		
		dpOutraSemana.setDayCellFactory(fabricaDias);
		
		dpOutraSemana.setOnAction(e -> {
			AtividadeSemanal anterior = atual;
			
			atual = servico.obterAtividadeSemanal(dpOutraSemana.getValue());
			
			if(atual == null){
				atual = anterior;
				return;
			} else {
				atualizar();
			}
		});
		
	}

	private TableColumn<ConteudoDidatico, String> obterColunaAvaliacao() {
		
		TableColumn<ConteudoDidatico, String> colunaConteudo = new TableColumn<>("Obs / Avaliação");
		colunaConteudo.setCellValueFactory(new PropertyValueFactory<>("obsAvaliacao"));
		colunaConteudo.setCellFactory(TextFieldTableCell.<ConteudoDidatico>forTableColumn());
		colunaConteudo.setOnEditCommit(e -> {
			ConteudoDidatico p = e.getRowValue();
			
			
			
			p.setObsAvaliacao(e.getNewValue());
			
			servico.salvarConteudo(p);
		});
		
		return colunaConteudo;
	}

	private TableColumn<ConteudoDidatico, String> obterColunaObjetivos() {
		
		TableColumn<ConteudoDidatico, String> colunaObjetivos = new TableColumn<>("Objetivos");
		colunaObjetivos.setCellValueFactory(new PropertyValueFactory<>("objetivos"));
		colunaObjetivos.setCellFactory(TextFieldTableCell.<ConteudoDidatico>forTableColumn());
		colunaObjetivos.setOnEditCommit(e -> {
			ConteudoDidatico p = e.getRowValue();
			
			
			
			p.setObjetivos(e.getNewValue());
			
			servico.salvarConteudo(p);
		});
		
		return colunaObjetivos;
		
	}

	private TableColumn<ConteudoDidatico, String> obterColunaEtapas() {
		
		TableColumn<ConteudoDidatico, String> colunaEtapas = new TableColumn<>("Etapas");
		colunaEtapas.setCellValueFactory(new PropertyValueFactory<>("etapas"));
		colunaEtapas.setCellFactory(TextFieldTableCell.<ConteudoDidatico>forTableColumn());
		colunaEtapas.setOnEditCommit(e -> {
			ConteudoDidatico p = e.getRowValue();
			
			
			
			p.setEtapas(e.getNewValue());
			
			servico.salvarConteudo(p);
		});
		
		return colunaEtapas;
		
	}

	private TableColumn<ConteudoDidatico, String> obterColunaMaterialUsado() {
		
		TableColumn<ConteudoDidatico, String> colunaMaterialUsado = new TableColumn<>("Material Usado");
		colunaMaterialUsado.setCellValueFactory(new PropertyValueFactory<>("materialUsado"));
		colunaMaterialUsado.setCellFactory(TextFieldTableCell.<ConteudoDidatico>forTableColumn());
		colunaMaterialUsado.setOnEditCommit(e -> {
			ConteudoDidatico p = e.getRowValue();
			
			
			
			p.setMaterialUsado(e.getNewValue());
			
			servico.salvarConteudo(p);
		});
		
		return colunaMaterialUsado;
		
	}

	private TableColumn<ConteudoDidatico, String> obterColunaConteudo() {
		
		TableColumn<ConteudoDidatico, String> colunaConteudo = new TableColumn<>("Conteúdo");
		colunaConteudo.setCellValueFactory(new PropertyValueFactory<>("conteudo"));
		colunaConteudo.setCellFactory(TextFieldTableCell.<ConteudoDidatico>forTableColumn());
		colunaConteudo.setOnEditCommit(e -> {
			ConteudoDidatico p = e.getRowValue();
			
			p.setConteudo(e.getNewValue());
			
			servico.salvarConteudo(p);
		});
		
		return colunaConteudo;
		
	}

	public void salvar(){
		atual.setAvaliacao(taAvaliacao.getText());
		atualMensal.setTema(tfTemaMes.getText());
		
		servico.salvarAtividadeSemanal(atual);
		servico.salvarAtividadeMensal(atualMensal);
	}
	
	@SuppressWarnings("unchecked")
	public void atualizar() {
		tfTemaMes.setText(atualMensal.getTema());
		taAvaliacao.setText(atual.getAvaliacao());
		
		List<String> linguagens = new ArrayList<String>();
		linguagens.add("pt");
		
		for(int i = 0; i < atual.getDias().size(); i++){
			TitledPane tpAtual = aDias.getPanes().get(i);
			AtividadeDiaria diaAtual = atual.getDias().get(i);
		
			tpAtual.setText(DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy")
											 .withLocale(Locale.forLanguageTag(Locale.lookupTag(LanguageRange.parse("pt"), linguagens)))
											 .format(diaAtual.getDia()));
			
			TableView<ConteudoDidatico> conteudo = new TableView<>();
			
			conteudo.getColumns().addAll(obterColunaConteudo(),
									 obterColunaMaterialUsado(),
									 obterColunaEtapas(),
									 obterColunaObjetivos(),
									 obterColunaAvaliacao());
			
			conteudo.getColumns().forEach(e -> {
				
				e.setPrefWidth((apresentador.obterPalco().getWidth() / conteudo.getColumns().size()) - 10);
				
			});
			
			conteudo.getItems().addAll(diaAtual.getConteudos());
			
			conteudo.setEditable(true);
			
			tpAtual.setContent(conteudo);
			
		}
		
		dpOutraSemana.setValue(atual.getSegunda());
	}

	@SuppressWarnings("unchecked")
	private void remover() {
		TitledPane selecionado = aDias.getExpandedPane();
		
		if(selecionado == null){
			
			ajuda.set("Selecione o dia em que se removerá o conteúdo.");
			
			return;
			
		} else {
			
			ajuda.set("");
	
		}

		TableView<ConteudoDidatico> conteudo = (TableView<ConteudoDidatico>) selecionado.getContent();
		
		TableViewSelectionModel<ConteudoDidatico> tsm = conteudo.getSelectionModel();
		
		if (tsm.isEmpty()) {

			ajuda.set("Selecione os conteudos que serão removidos");
			
			return;
		}
		
		// Get all selected row indices in an array
		ObservableList<Integer> list = tsm.getSelectedIndices();
		Integer[] selectedIndices = new Integer[list.size()];
		selectedIndices = list.toArray(selectedIndices);

		// Sort the array
		Arrays.sort(selectedIndices);
		
		// Delete rows (last to first)
		ConteudoDidatico p;
		for(int i = selectedIndices.length - 1; i >= 0; i--) {
			tsm.clearSelection(selectedIndices[i].intValue());
			 p = conteudo.getItems().remove(selectedIndices[i].intValue());
			 servico.removerConteudo(atual.getDias().get(aDias.getPanes().indexOf(selecionado)), p);
		}
	}

	@SuppressWarnings("unchecked")
	private void adicionar() {
		TitledPane selecionado = aDias.getExpandedPane();
		
		if(selecionado == null){
			
			ajuda.set("Selecione o dia que receberá o conteúdo.");
			
			return;
			
		} else {
			
			ajuda.set("");
			
		}
		
		TableView<ConteudoDidatico> conteudo = (TableView<ConteudoDidatico>) selecionado.getContent();
		
		ConteudoDidatico cd = new ConteudoDidatico();
		
		AtividadeDiaria ad = atual.getDias().get(aDias.getPanes().indexOf(selecionado));
		
		servico.salvarConteudo(atual, ad, cd);
		conteudo.getItems().add(cd);
	}
}