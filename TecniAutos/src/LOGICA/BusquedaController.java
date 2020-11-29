 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGICA;

import BD.Registro;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class BusquedaController implements Initializable {

    private Arranque admin;
    private CrudController crud;
    private EntityManager em = Persistence.createEntityManagerFactory("TecniAutosPU").createEntityManager();
    
    @FXML
    private TableColumn<?, ?> EtiquetaFecha;
    @FXML
    private TableColumn<?, ?> EtiquetaPlaca;
    @FXML
    private TableColumn<?, ?> EtiquetaMarca;
    @FXML
    private TableColumn<?, ?> EtiquetaKilometraje;
    @FXML
    private TableColumn<?, ?> EtiquetaTecnico;
    @FXML
    private TableColumn<?, ?> EtiquetaCosto;
    @FXML
    private TableView<Registro> infoRegistro;
    @FXML
    private TableColumn<?, ?> EtiquetaCliente;
    @FXML
    private TableColumn<?, ?> EtiquetaCedula;
    @FXML
    private TextField placa;
    @FXML
    private TextField cliente;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(placa.getText().isEmpty()){
            System.out.println("Entre");
            EtiquetaFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
            EtiquetaCliente.setCellValueFactory(new PropertyValueFactory<>("Cliente"));
            EtiquetaCedula.setCellValueFactory(new PropertyValueFactory<>("Cedula"));
            EtiquetaPlaca.setCellValueFactory(new PropertyValueFactory<>("Placa"));
            EtiquetaMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
            EtiquetaKilometraje.setCellValueFactory(new PropertyValueFactory<>("Kilometraje"));
            EtiquetaTecnico.setCellValueFactory(new PropertyValueFactory<>("Tecnico"));
            EtiquetaCosto.setCellValueFactory(new PropertyValueFactory<>("Costo"));
        
            Query valida = em.createNamedQuery("Registro.findAll");
            List<Registro> listaRegistros = valida.getResultList();
            Calendar fechas = Calendar.getInstance();
            
            for(Registro i : listaRegistros){
            fechas.setTime(i.getFecha());
            int anio = (fechas.get(Calendar.YEAR) - 1900);
            int mes = fechas.get(Calendar.MONTH);
            int dia = fechas.get(Calendar.DAY_OF_MONTH);
            i.setFecha(new Date(anio,mes,dia));
            }
            
            infoRegistro.setItems(FXCollections.observableArrayList(listaRegistros));
        } else{
            buscarRegistro();
        }
        
        infoRegistro.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() > 1){
                    int idregistro = infoRegistro.getSelectionModel().getSelectedItem().getIdregistro();
                    admin.Crud(idregistro);
                    admin.setRegistro(infoRegistro.getSelectionModel().getSelectedItem());
                    
                }
            }
        });
    }   

    public Arranque getAdmin() {
        return admin;
    }

    public void setAdmin(Arranque admin) {
        this.admin = admin;
    }

    @FXML
    private void Volver(MouseEvent event) {
        admin.Crud(0);
        admin.cerrarVentana("stage1");
    }
    
    @FXML
    public void buscarRegistro(){
        if(!placa.getText().equalsIgnoreCase("")){
            EtiquetaFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
            EtiquetaCliente.setCellValueFactory(new PropertyValueFactory<>("Cliente"));
            EtiquetaCedula.setCellValueFactory(new PropertyValueFactory<>("Cedula"));
            EtiquetaPlaca.setCellValueFactory(new PropertyValueFactory<>("Placa"));
            EtiquetaMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
            EtiquetaKilometraje.setCellValueFactory(new PropertyValueFactory<>("Kilometraje"));
            EtiquetaTecnico.setCellValueFactory(new PropertyValueFactory<>("Tecnico"));
            EtiquetaCosto.setCellValueFactory(new PropertyValueFactory<>("Costo"));
            
            Calendar fecha = Calendar.getInstance();
            
            String query = "SELECT * FROM registro WHERE placa LIKE '"+placa.getText().toUpperCase()+"%' Or cliente LIKE '"+placa.getText()+"%';";
            Query consulta = em.createNativeQuery(query, Registro.class);
            List<Registro> l = consulta.getResultList();
            
            for(Registro i : l){
            fecha.setTime(i.getFecha());
            int anio = (fecha.get(Calendar.YEAR) - 1900);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            i.setFecha(new Date(anio,mes,dia));
        }
        
            infoRegistro.setItems(FXCollections.observableArrayList(l));
        }
        
    }
    
    @FXML
    public void busquedaCliente(){
        if(!cliente.getText().equalsIgnoreCase("")){
            EtiquetaFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
            EtiquetaCliente.setCellValueFactory(new PropertyValueFactory<>("Cliente"));
            EtiquetaCedula.setCellValueFactory(new PropertyValueFactory<>("Cedula"));
            EtiquetaPlaca.setCellValueFactory(new PropertyValueFactory<>("Placa"));
            EtiquetaMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
            EtiquetaKilometraje.setCellValueFactory(new PropertyValueFactory<>("Kilometraje"));
            EtiquetaTecnico.setCellValueFactory(new PropertyValueFactory<>("Tecnico"));
            EtiquetaCosto.setCellValueFactory(new PropertyValueFactory<>("Costo"));
            
            Calendar fecha = Calendar.getInstance();
            
            String query = "SELECT * FROM registro WHERE cliente LIKE '"+cliente.getText().toUpperCase()+"%';";
            Query consulta = em.createNativeQuery(query, Registro.class);
            List<Registro> l = consulta.getResultList();
            
            for(Registro i : l){
            fecha.setTime(i.getFecha());
            int anio = (fecha.get(Calendar.YEAR) - 1900);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            i.setFecha(new Date(anio,mes,dia));
        }
        
            infoRegistro.setItems(FXCollections.observableArrayList(l));
        }
    }
}
