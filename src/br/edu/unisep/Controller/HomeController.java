package br.edu.unisep.Controller;

import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.utils.UsuarioUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class HomeController extends AppController {

    @FXML private AnchorPane container;

    @FXML private Label txtPagina;
    @FXML private Label txtUsuario;

    @FXML private ImageView imgProjetos;
    @FXML private ImageView imgUsuarios;

    @FXML private HBox menu;

    @Override
    protected void onInit() {
        if (UsuarioUtils.getUsuario().getTipo() == 2){
            txtPagina.setText("Meus Projetos");
            openScene(container, "../view/meusProjetos.fxml");
        }
        configurarMenu();
    }

    public void abrirProjetos (MouseEvent event){
        txtPagina.setText("Projetos");
        openScene(container, "../view/Projetos.fxml");
    }

    private void configurarMenu(){
        var usuario = UsuarioUtils.getUsuario();

        txtUsuario.setText(usuario.getNome());

        if (usuario.getTipo() != 1){
            menu.getChildren().remove(imgProjetos);
            menu.getChildren().remove(imgUsuarios);
        }
    }

    public void sair (MouseEvent event){
        System.exit(0);
    }
}
