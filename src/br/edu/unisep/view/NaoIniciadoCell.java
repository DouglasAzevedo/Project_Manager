package br.edu.unisep.view;

import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.TarefaVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NaoIniciadoCell extends ListCell<TarefaVO> {

    private AnchorPane cell;
    private Label lblDescricao;
    private Label lblProjeto;

    public NaoIniciadoCell(){
        try {
            cell = FXMLLoader.load(getClass().getResource("item_Naoiniciado.fxml"));

            lblDescricao = (Label) cell.lookup("lblDescricao");
            lblProjeto = (Label) cell.lookup("lblProjeto");

            lblDescricao.setText("");
            lblProjeto.setText("");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(TarefaVO tarefa, boolean vazio) {
        super.updateItem(tarefa, vazio);

        if(!vazio){
            lblDescricao.setText(tarefa.getDescricao());
            lblProjeto.setText(tarefa.getProjeto().getDescricao());

            setGraphic(cell);
        }else{
            setGraphic(null);
        }

    }



}
