package br.edu.unisep.view;

import br.edu.unisep.model.vo.TarefaVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class GenericCell extends ListCell<TarefaVO> {

    private AnchorPane cell;
    private Label lblDescricao;
    private Label lblProjeto;
    private Label lblText;
    private Label lblIniciado;
    private Label lblTermino;
    private DateTimeFormatter fmt;

    public GenericCell() {

        try {
            cell = FXMLLoader.load(getClass().getResource("project_Generic.fxml"));

            lblDescricao = (Label) cell.lookup("#lblDescricao");
            lblProjeto = (Label) cell.lookup("#lblProjeto");
            lblText = (Label) cell.lookup("#lblText");
            lblIniciado = (Label) cell.lookup("#lblIniciado");
            lblTermino = (Label) cell.lookup("#lblTermino");

            fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            lblDescricao.setText("");
            lblProjeto.setText("");
            lblText.setText("");
            lblIniciado.setText("");
            lblTermino.setText("");
            cell.setPrefWidth(0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(TarefaVO tarefa, boolean vazio) {
        super.updateItem(tarefa, vazio);

        if (!vazio) {
            lblDescricao.setText(tarefa.getDescricao());
            lblProjeto.setText(tarefa.getProjeto().getDescricao());
            if (tarefa.getStatus() == 2) {
                lblText.setText("Iniciada em: ");
                lblIniciado.setText(tarefa.getInicio().format(fmt));
            } else {
                lblText.setText("Periodo de Desenvolvimento: ");
                lblIniciado.setText(tarefa.getInicio().format(fmt));
                lblTermino.setText(tarefa.getTermino().format(fmt));
            }
            setGraphic(cell);
        } else {
            setGraphic(null);
        }
    }
}