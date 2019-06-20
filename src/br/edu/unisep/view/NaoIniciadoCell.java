package br.edu.unisep.view;

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("item_Naoiniciado.fxml"));
            cell = loader.load();

            lblDescricao = (Label) cell.lookup("#lblDescricao");
            lblProjeto = (Label) cell.lookup("#lblProjeto");

            lblDescricao.setText("");
            lblProjeto.setText("");

            cell.setPrefWidth(0d);
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
