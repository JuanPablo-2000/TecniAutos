/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGICA;

import BD.Registro;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Juan
 */
public class Arranque extends Application {
    
    private AnchorPane primerEscenario;
    private AnchorPane segundoEscenario;
    private AnchorPane tercerEscenario;
    
    private Stage stage1 = new Stage();
    private Stage stage2;
    private Stage stage3;
    
    private Registro registro = new Registro();
    
    @Override
    public void start(Stage stage) {
        stage1 = stage;
        stage2 = new Stage();
        stage3 = new Stage();
        
        stage1.setTitle("Registro TecniAutos");
        stage2.setTitle("Administrarción de Registros TecniAutos");
        stage3.setTitle("Agregar Repuestos");
        
        Image icon = new Image("/IMG/coche.png");
        
        stage1.getIcons().add(icon);
        stage2.getIcons().add(icon);
        stage3.getIcons().add(icon);
        
        stage1.initStyle(StageStyle.UNDECORATED);
        stage2.initStyle(StageStyle.UNDECORATED);
        stage3.initStyle(StageStyle.UNDECORATED);
        
        Crud(0);
    }

    public void Registro(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Arranque.class.getResource("/FXML/Busqueda.fxml"));
            
            primerEscenario = (AnchorPane) loader.load();
            
            Scene escena = new Scene(primerEscenario);
            stage1.setScene(escena);
            
            BusquedaController control = loader.getController();
            control.setAdmin(this);
            
            stage1.show();
            
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    public void Crud(int idRegistro){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Arranque.class.getResource("/FXML/Crud.fxml"));
            
            segundoEscenario = (AnchorPane) loader.load();
            
            Scene escena = new Scene(segundoEscenario);
            stage2.setScene(escena);
            
            CrudController control = loader.getController();
            control.tomarBusqueda(idRegistro);
            cerrarVentana("stage1");
            control.setAdmin(this);
            
            stage2.show();
            
        } catch (IOException e) {
        }
    }
    
    public void Repuestos(Integer i, Registro r){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Arranque.class.getResource("/FXML/Repuestos.fxml"));
            
            tercerEscenario = (AnchorPane) loader.load();
            
            Scene escena = new Scene(tercerEscenario);
            stage3.setScene(escena);
            
            RepuestosController control = loader.getController();
            control.mostrar_repuesto(i);
            control.construir(r);
            control.setAdmin(this);
            
            stage3.show();
        } catch (IOException e) {
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void cerrarVentana(String ventana){
        if(ventana.equalsIgnoreCase("stage1")){
            stage1.close();
        }else if(ventana.equalsIgnoreCase("stage3")){
            stage3.close();
        } else {
            stage2.close();
        } 
    }
    
    public void setRegistro(Registro r){
        this.registro = r;
    }
    public Registro getRegistro(){
        return registro;
    }
    
}
