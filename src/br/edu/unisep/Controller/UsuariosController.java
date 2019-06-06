package br.edu.unisep.Controller;

import br.edu.unisep.fx.annotation.ColValueMap;
import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.annotation.IntToString;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.UsuarioDAO;
import br.edu.unisep.model.vo.UsuarioVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UsuariosController extends AppController {

    @FXML private TableView<UsuarioVO> tabUsuarios;

    @FXColumn(property = "ds_nome")
    @FXML private TableColumn<UsuarioVO, String> colNome;

    @FXColumn(property = "ds_email")
    @FXML private TableColumn<UsuarioVO, String> colEmail;

    @FXColumn(property = "tp_usuario")
    @ColValueMap({
            @IntToString(from = 1, to = "Admin"),
            @IntToString(from = 2, to = "Gerente"),
            @IntToString(from = 3, to = "Desenvolvedor")
    })
    @FXML private  TableColumn<UsuarioVO, String> colTipo;

    private ObservableList<UsuarioVO> usuarios;

    @Override
    protected void onInit() {
        usuarios = FXCollections.observableArrayList();
        tabUsuarios.setItems(usuarios);

        listar();
    }

    public void listar(){
        var dao = new UsuarioDAO();
        var lista = dao.listarPorTipo(2);

        usuarios.setAll(lista);
    }
}
