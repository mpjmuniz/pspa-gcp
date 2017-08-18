package org.pspa.gcp.visao;

import org.pspa.gcp.modelo.Aluno;
import org.pspa.gcp.modelo.Inscrito;
import org.pspa.gcp.modelo.repositorios.RepositorioInscrito;
import org.springframework.context.ApplicationContext;

import javafx.scene.control.Button;

public class VisaoInscritos extends VisaoCadastros<Inscrito>{
	
	protected VisaoInscritos(ApplicationContext contexto) {
		super(Inscrito.class);
		
		bInscrever = new Button("Inscrever Aluno");
		bInscrever.setOnAction(e -> inscrever());
		bInscrever.setPrefWidth(1000);
		vbListagem.getChildren().add(bInscrever);
		
		repositorio = contexto.getBean(RepositorioInscrito.class);
	}

	@Override
	public void deletar(Inscrito objeto) {
		repositorio.delete(objeto);
	}
	
	protected void inscrever() {
		
		Inscrito objeto;
		Aluno inscrito;
		
		if(lElementos.getSelectionModel().isEmpty()){
			
			ajuda.set("Na£o ha nenhuma inscricao selecionada. Criarei o objeto descrito.");
			
			if(lCampos.dadosEstaoValidos()){
				lCampos.construirObjeto();

				objeto = lCampos.obterObjeto();
			
				lCampos.definirObjeto(null);
			} else {
				ajuda.set("Alguns dos dados estao invalidos. Verifique os campos envoltos em vermelho.");
				return;
			}			
		} else {
			objeto = lElementos.getSelectionModel().getSelectedItem();
		}
		
		inscrito = objeto.inscreverAluno();
		
		lElementos.getItems().add(objeto);
		
		persistir(objeto);
		
		apresentador.designarInscricao(inscrito);
	}

	@Override
	protected Inscrito atualizarElemento(Inscrito elemento) {
		if(elemento == null) return null;
		return repositorio.findOne(elemento.getMid());
	}
}
