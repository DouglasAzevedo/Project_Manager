package br.edu.unisep.controller;

import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.TarefaDAO;
import br.edu.unisep.model.vo.TarefaVO;
import br.edu.unisep.utils.UsuarioUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import java.time.LocalDateTime;

public class MinhasTarefasController extends AppController {

    @FXML private ListView<TarefaVO> listNaoIniciado;

    @FXML private ListView<TarefaVO> listEmAndamento;

    @FXML private ListView<TarefaVO> listFinalizados;

    private ObservableList<TarefaVO> tarefasInicio;
    private ObservableList<TarefaVO> tarefasMeio;
    private ObservableList<TarefaVO> tarefasFim;


    @Override
    protected void onInit() {
        tarefasInicio = FXCollections.observableArrayList();
        tarefasMeio = FXCollections.observableArrayList();
        tarefasFim = FXCollections.observableArrayList();

        listarTarefas(1);
        listarTarefas(2);
        listarTarefas(3);

        listNaoIniciado.setItems(tarefasInicio);

        listEmAndamento.setItems(tarefasMeio);

        listFinalizados.setItems(tarefasFim);

    }

    private void listarTarefas(Integer status){
        var dao = new TarefaDAO();
        var lista = dao.listarPorStatus(UsuarioUtils.getUsuario(), status);

        if(status == 1){
            tarefasInicio.setAll(lista);
        }else if(status == 2){
            tarefasMeio.setAll(lista);
        }else{
            tarefasFim.setAll(lista);
        }

    }

    public void dragStart(MouseEvent event) {
        var dragboard = listNaoIniciado.startDragAndDrop(TransferMode.MOVE);

        var pos = listNaoIniciado.getSelectionModel().getSelectedIndex();

        var content = new ClipboardContent();
        content.putString(String.valueOf(pos));

        dragboard.setContent(content);

        event.consume();
    }

    public void dragStartMeio(MouseEvent event) {
        var dragboard = listEmAndamento.startDragAndDrop(TransferMode.MOVE);

        var pos = listEmAndamento.getSelectionModel().getSelectedIndex();

        var content = new ClipboardContent();
        content.putString(String.valueOf(pos));

        dragboard.setContent(content);

        event.consume();
    }

    public void dragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    public void dropMeio(DragEvent event) {

        var dragboard = event.getDragboard();
        var pos = dragboard.getString();

        var tarefa = tarefasMeio.get(Integer.parseInt(pos));

        var dao = new TarefaDAO();
        tarefa.setStatus(2);
        tarefa.setInicio(LocalDateTime.now());
        dao.alterar(tarefa);

        event.consume();
    }

    public void dropFim(DragEvent event) {

        var dragboard = event.getDragboard();
        var pos = dragboard.getString();

        var tarefa = tarefasFim.get(Integer.parseInt(pos));

        var dao = new TarefaDAO();
        tarefa.setStatus(3);
        tarefa.setTermino(LocalDateTime.now());
        dao.alterar(tarefa);

        event.consume();
    }
}
