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

    @FXML private AnchorPane conteudo;

    @FXML private Label titulo;
    @FXML private Label nomeUsuario;


    @FXML private ImageView imgProjetos;
    @FXML private ImageView imgUsuarios;

    @FXML private HBox menu;

    @Override
    protected void onInit() {
        configurarMenu();

        if (UsuarioUtils.getUsuario().getTipo()==2){
            titulo.setText("Meus Projetos");
            openScene(conteudo,"../view/meusProjetos.fxml");
        }
    }

    public void configurarMenu(){
        var usuario = UsuarioUtils.getUsuario();
        if(usuario.getTipo() != 1){
            menu.getChildren().remove(imgProjetos);
            menu.getChildren().remove(imgUsuarios);
        }
        var tipo = "";
        if(usuario.getTipo() == 2){
            tipo = "Administrador";
        }else if (usuario.getTipo() == 2){
            tipo = "Gerente de Projetos";
        }else{
            tipo = "Desenvolvedor";
        }

        nomeUsuario.setText("Olá, " + usuario.getNome() + "\n" + tipo);
    }

    public void logout(MouseEvent event){
        System.exit(0);
    }

    public void abrirProjetos(MouseEvent event){
        titulo.setText("Projetos");
        openScene(conteudo,"../view/lprojetos.fxml");
    }
}
