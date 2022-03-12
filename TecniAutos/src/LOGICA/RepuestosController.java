/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGICA;

import BD.Descripcion;
import BD.Registro;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Juan Pablo Mera
 */
public class RepuestosController implements Initializable {

    private Arranque admin;
    private Descripcion descripcion = new Descripcion();
    private Registro registro = new Registro();
    private Integer integer;
    private EntityManager em;
    
    @FXML
    private TableView<Descripcion> TablaRepuestos;
    @FXML
    private TableColumn<?, ?> ColCantidad;
    @FXML
    private TableColumn<?, ?> ColRepuesto;
    @FXML
    private TableColumn<?, ?> ColValor;
    @FXML
    private TextField Cantidad;
    @FXML
    private TextField Repuesto;
    @FXML
    private TextField Valor;
    @FXML
    private ImageView BotonAgregar;
    @FXML
    private Label Agregar;
    @FXML
    private ImageView botonActualizar;
    @FXML
    private Label labelActualizar;
    @FXML
    private ImageView botonEliminar;
    @FXML
    private Label LabelEliminar;
    @FXML
    private AnchorPane Repuestos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        botonActualizar.setDisable(true);
        labelActualizar.setDisable(true);
        
        botonEliminar.setDisable(true);
        LabelEliminar.setDisable(true);
        
        Cantidad.setFocusTraversable(true);
        Repuesto.setFocusTraversable(true);
        Valor.setFocusTraversable(true);
        
        descripcion = new Descripcion();
        em = Persistence.createEntityManagerFactory("TecniAutosPU").createEntityManager();
        
        TablaRepuestos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() > 1){
                    descripcion = TablaRepuestos.getSelectionModel().getSelectedItem();
                    
                    Cantidad.setText(descripcion.getCantidad());
                    Repuesto.setText(descripcion.getRepuesto());
                    Valor.setText(descripcion.getValor().toString());
                    
                    botonActualizar.setDisable(false);
                    labelActualizar.setDisable(false);
        
                    botonEliminar.setDisable(false);
                    LabelEliminar.setDisable(false);
                }
            }
        });
        
        Repuestos.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.TAB){
                Cantidad.requestFocus();
            }
        });
        Repuestos.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER){
                agregar_registro();
            }
        });
    }

    public void construir(Registro r){
        this.registro = r;
    }
    
    public Arranque getAdmin() {
        return admin;
    }
    
    public void setAdmin(Arranque admin) {
        this.admin = admin;
    }
    
    public void setIdRegistro(Integer idregistro){
        this.integer = idregistro;
    }
    
    public Integer getIdRegistro(){
        return integer;
    }
    
    public void mostrar_repuesto(Integer i){
        ColCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        ColRepuesto.setCellValueFactory(new PropertyValueFactory<>("Repuesto"));
        ColValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        
        String query = "SELECT * FROM descripcion WHERE idregistro = "+i+";";
        Query consulta = em.createNativeQuery(query, Descripcion.class);
        List<Descripcion> d = consulta.getResultList();
        
        TablaRepuestos.setItems(FXCollections.observableArrayList(d));
        
        setIdRegistro(i);
    }
    
    @FXML
    public void Volver(){
        admin.Crud(registro.getIdregistro());
        admin.cerrarVentana("stage3");
    }

    @FXML
    private void agregar_registro() {
        
        if(Cantidad.getText().equalsIgnoreCase("") 
          || Repuesto.getText().equalsIgnoreCase("") 
          || Valor.getText().equalsIgnoreCase("")){
            
            JOptionPane.showMessageDialog(null, "Por Favor Llene Todos Los Campos");
            
        } else {
            BigInteger valor = new BigInteger(Valor.getText());
            
            descripcion.setIdregistro(registro);
            descripcion.setCantidad(Cantidad.getText());
            descripcion.setRepuesto(Repuesto.getText());
            descripcion.setValor(valor);
            
            em.getTransaction().begin();
            em.persist(descripcion);
            em.getTransaction().commit();
            
            Cantidad.setText("");
            Repuesto.setText("");
            Valor.setText("");
            
            JOptionPane.showMessageDialog(null, "Repuesto Agregado Correctamente");
            
            mostrar_repuesto(getIdRegistro());
            descripcion = new Descripcion();
        }
    }
    
    @FXML
    public void eliminar(){
        String query = "DELETE FROM descripcion WHERE idrepuesto = "+descripcion.getIdrepuesto()+";";
        Query consulta = em.createNativeQuery(query);
        
        em.getTransaction().begin();
        consulta.executeUpdate();
        em.getTransaction().commit();
        
        Cantidad.setText("");
        Repuesto.setText("");
        Valor.setText("");
        
        JOptionPane.showMessageDialog(null, "Se ha Eliminado Correctamente el Repuesto");
        
        mostrar_repuesto(getIdRegistro());
        descripcion = new Descripcion();
    }
    @FXML
    public void actualizar(){
        BigInteger valor = new BigInteger(Valor.getText());
        
        descripcion.setIdrepuesto(descripcion.getIdrepuesto());
        descripcion.setCantidad(Cantidad.getText());
        descripcion.setRepuesto(Repuesto.getText());
        descripcion.setValor(valor);
        
        em.getTransaction().begin();
        em.merge(descripcion);
        em.getTransaction().commit();
        
        Cantidad.setText("");
        Repuesto.setText("");
        Valor.setText("");
        
        JOptionPane.showMessageDialog(null, "Se ha Actualizado Correctamente el Repuesto");
        
        TablaRepuestos.getItems().clear();
        mostrar_repuesto(getIdRegistro());
        descripcion = new Descripcion();
    }

}
