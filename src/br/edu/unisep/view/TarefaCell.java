package br.edu.unisep.view;

import br.edu.unisep.model.vo.TarefaVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

public class TarefaCell extends ListCell<TarefaVO> {

    private AnchorPane cell;

    private Label titulo;

    private Label responsalvel;

    private Label dtInicio;

    private Label dtTermino;

    public TarefaCell(){
        try {
            cell = FXMLLoader.load(getClass().getResource("item_Tarefa.fxml"));

            titulo = (Label) cell.lookup("#lblTitulo");
            responsalvel = (Label) cell.lookup("#lblResp");
            dtInicio = (Label) cell.lookup("#lblInicio");
            dtTermino = (Label) cell.lookup("#lblTermino");

            cell.setPrefWidth(0);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(TarefaVO tarefa, boolean vazio) {
        super.updateItem(tarefa, vazio);

        if (!vazio){

            titulo.setText(tarefa.getDescricao());
            responsalvel.setText(tarefa.getUsuario().getNome());

            var df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dtInicio.setText(df.format(tarefa.getInicio()));
            dtTermino.setText(df.format(tarefa.getTermino()));

            setGraphic(cell);
        } else {
            setGraphic(null);
        }
    }
}
