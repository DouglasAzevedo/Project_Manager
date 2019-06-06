package br.edu.unisep.Controller;

import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.ProjetoDAO;
import br.edu.unisep.model.dao.TarefaDAO;
import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.TarefaVO;
import br.edu.unisep.utils.UsuarioUtils;
import br.edu.unisep.view.ProjetoCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MeusProjetosController extends AppController {

    @FXML
    private ListView<ProjetoVO> lstProjetos;

    @FXML
    private ListView<TarefaVO> lstTarefas;

    private ObservableList<ProjetoVO> projetos;
    private ObservableList<TarefaVO> tarefas;


    @Override
    protected void onInit() {
        projetos = FXCollections.observableArrayList();
        tarefas = FXCollections.observableArrayList();

        listarProjetos();
    }

    public void listarProjetos() {
        var dao = new ProjetoDAO();
        var lista = dao.listarPorGerente(UsuarioUtils.getUsuario());
        projetos.setAll(lista);

        lstProjetos.setItems(projetos);
        lstProjetos.setCellFactory(lstView -> new ProjetoCell());
    }

    public void listarTarefa(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            var dao = new TarefaDAO();
            var projeto = lstProjetos.getSelectionModel().getSelectedItem();
            var lista = dao.listarPorProjeto(projeto);

            tarefas.setAll(lista);

            lstTarefas.setItems(tarefas);
        }
    }
}