package br.edu.unisep.view;

import br.edu.unisep.model.vo.ProjetoVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProjetoCell extends ListCell<ProjetoVO> {

    private AnchorPane cell;

    private Label nome;
    private Label cliente;

    public ProjetoCell(){

        try {
            cell = FXMLLoader.load(getClass().getResource("item_projeto.fxml"));

            nome = (Label) cell.lookup("#lblTitulo");
            cliente = (Label) cell.lookup("#lblCliente");

            cell.setPrefWidth(0);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(ProjetoVO projeto, boolean vazio) {
        super.updateItem(projeto, vazio);

        if (!vazio){
            nome.setText(projeto.getTitulo());
            cliente.setText(projeto.getCliente().getNome());

            setGraphic(cell);
        } else {
            setGraphic(null);
        }
    }
}
