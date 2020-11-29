/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGICA;

import BD.Descripcion;
import BD.Registro;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Juan
 */
public class CrudController implements Initializable {

    private Arranque admin;
    private Registro registro = new Registro();
    private EntityManager em;
    private int tam;

    @FXML
    private ImageView guardar;
    @FXML
    private ImageView modificar;
    @FXML
    private ImageView eliminar;
    @FXML
    private Label etiquetaGuardar;
    @FXML
    private Label etiquetaModificar;
    @FXML
    private Label etiquetaEliminar;
    @FXML
    private ImageView buscar;
    @FXML
    private Label EtiquetaBuscar;
    @FXML
    private ImageView Cerrar;
    @FXML
    private TextField marcaVehiculo;
    @FXML
    private TextField placaVehiculo;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField kilometraje;
    @FXML
    private TextField tecnicoAsignado;
    @FXML
    private TextField costo;
    @FXML
    private TableView<Descripcion> TablaRepuestos;
    @FXML
    private TableColumn<?, ?> ColCantidad;
    @FXML
    private TableColumn<?, ?> ColRepuesto;
    @FXML
    private TableColumn<?, ?> ColValor;
    @FXML
    private ImageView AgregarRepuesto;
    @FXML
    private Label Agregar;
    @FXML
    private Label Repuesto;
    @FXML
    private TextField cedula;
    @FXML
    private TextField cliente;
    @FXML
    private AnchorPane TecniAutos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fecha.setFocusTraversable(true);
        costo.setDisable(true);
        em = Persistence.createEntityManagerFactory("TecniAutosPU").createEntityManager();
        
        TecniAutos.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.TAB){
                fecha.requestFocus();
                
            }
        });
        
        TecniAutos.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER){
                Crear();
            }
        });
        
    }

    public Arranque getAdmin() {
        return admin;
    }

    public void setAdmin(Arranque admin) {
        this.admin = admin;
    }

    public ImageView getModificar() {
        return modificar;
    }

    public void setModificar(ImageView modificar) {
        this.modificar = modificar;
    }

    public ImageView getEliminar() {
        return eliminar;
    }

    public void setEliminar(ImageView eliminar) {
        this.eliminar = eliminar;
    }

    public Label getEtiquetaModificar() {
        return etiquetaModificar;
    }

    public void setEtiquetaModificar(Label etiquetaModificar) {
        this.etiquetaModificar = etiquetaModificar;
    }

    public Label getEtiquetaEliminar() {
        return etiquetaEliminar;
    }

    public void setEtiquetaEliminar(Label etiquetaEliminar) {
        this.etiquetaEliminar = etiquetaEliminar;
    }

    public TextField getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(TextField marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public TextField getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(TextField placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public DatePicker getFecha() {
        return fecha;
    }

    public void setFecha(DatePicker fecha) {
        this.fecha = fecha;
    }

    public TextField getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(TextField kilometraje) {
        this.kilometraje = kilometraje;
    }

    public TextField getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void setTecnicoAsignado(TextField tecnicoAsignado) {
        this.tecnicoAsignado = tecnicoAsignado;
    }
    
    public TextField getCosto() {
        return costo;
    }

    public void setCosto(TextField costo) {
        this.costo = costo;
    }
    
    @FXML
    public void Crear(){
        
        if(kilometraje.getText().equalsIgnoreCase("") || placaVehiculo.getText().equalsIgnoreCase("") 
          || marcaVehiculo.getText().equalsIgnoreCase("") || fecha.toString().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "Llene los Campos Correctamente...");
            
        }else{
            Date fechaParseo = java.sql.Date.valueOf(fecha.getValue());
            BigInteger costoparseo = new BigInteger("0");
            
            if(!costo.getText().equalsIgnoreCase("")){
                costoparseo = new BigInteger(costo.getText());
            }
            registro.setCliente(cliente.getText().toUpperCase());
            registro.setCedula(cedula.getText());
            registro.setPlaca(placaVehiculo.getText().toUpperCase());
            registro.setMarca(marcaVehiculo.getText());
            registro.setKilometraje(kilometraje.getText());
            registro.setTecnico(tecnicoAsignado.getText());
            registro.setCosto(costoparseo);
            registro.setFecha(fechaParseo);
            
            em.getTransaction().begin();
            em.persist(registro);
            em.getTransaction().commit();
            
            JOptionPane.showMessageDialog(null, "El Registro Se Creo Correctamente");
            
            fecha.setValue(null);
            cliente.setText("");
            cedula.setText("");
            placaVehiculo.setText("");
            marcaVehiculo.setText("");
            kilometraje.setText("");
            tecnicoAsignado.setText("");
            costo.setText("");
        }
    }
    
    @FXML
    public void Modificar(){
        AgregarRepuesto.setDisable(false);
        Agregar.setDisable(false);
        Repuesto.setDisable(false);
        
        Date fechaParseo = java.sql.Date.valueOf(fecha.getValue());
        
        registro.setIdregistro(registro.getIdregistro());
        registro.setCliente(cliente.getText().toUpperCase());
        registro.setCedula(cedula.getText());
        registro.setPlaca(placaVehiculo.getText().toUpperCase());
        registro.setMarca(marcaVehiculo.getText());
        registro.setKilometraje(kilometraje.getText());
        registro.setTecnico(tecnicoAsignado.getText());
        registro.setCosto(costoTotal());
        registro.setFecha(fechaParseo);
        
        em.getTransaction().begin();
        em.merge(registro);
        em.getTransaction().commit();
        
        JOptionPane.showMessageDialog(null, "Registro Modificado Correctamente");
        
        registro.setCliente(cliente.getText());
        registro.setCedula(cedula.getText());
        registro.setPlaca(placaVehiculo.getText().toUpperCase());
        registro.setMarca(marcaVehiculo.getText());
        registro.setKilometraje(kilometraje.getText());
        registro.setTecnico(tecnicoAsignado.getText());
        registro.setCosto(costoTotal());
        registro.setFecha(fechaParseo);
    }
    
    @FXML
    public void Eliminar(){
        Date fechaParseo = java.sql.Date.valueOf(fecha.getValue());
        
        String query = "DELETE FROM registro WHERE fecha='"+fechaParseo+"' AND placa='"+placaVehiculo.getText().toUpperCase()+"';";
        Query consulta = em.createNativeQuery(query);
        em.getTransaction().begin();
        consulta.executeUpdate();
        em.getTransaction().commit();
        
        fecha.setValue(null);
        cliente.setText("");
        cedula.setText("");
        placaVehiculo.setText("");
        marcaVehiculo.setText("");
        kilometraje.setText("");
        tecnicoAsignado.setText("");
        costo.setText("");
        
        JOptionPane.showMessageDialog(null, "Se Elimino Corectamente");
    }
    
    @FXML
    public void Buscar(){
        admin.Registro();
        admin.cerrarVentana("");
    }
    
    @FXML
    public void Cerrar(){
        admin.cerrarVentana("");
    }
    
    private static boolean isNumeric(String dato){
        try {
            Integer.parseInt(dato);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public void tomarBusqueda(int idRegistro){
        
        if(idRegistro > 0){
            String query = "SELECT * FROM registro WHERE idregistro="+idRegistro+";";
            Query consulta = em.createNativeQuery(query,Registro.class);
            List<Registro> r = consulta.getResultList();
            
            Date fechaAntigua = r.get(0).getFecha();
            LocalDate fechaNueva = Instant.ofEpochMilli(fechaAntigua.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            
            registro = em.find(Registro.class, r.get(0).getIdregistro());
            fecha.setValue(fechaNueva);
            cliente.setText(r.get(0).getCliente());
            cedula.setText(r.get(0).getCedula());
            marcaVehiculo.setText(r.get(0).getMarca());
            placaVehiculo.setText(r.get(0).getPlaca());
            kilometraje.setText(r.get(0).getKilometraje());
            tecnicoAsignado.setText(r.get(0).getTecnico());
            costo.setText(r.get(0).getCosto().toString());
        
            modificar.setDisable(false);
            eliminar.setDisable(false);
            etiquetaModificar.setDisable(false);
            etiquetaEliminar.setDisable(false);
            AgregarRepuesto.setDisable(false);
            Agregar.setDisable(false);
            Repuesto.setDisable(false);
            
            mostrar_repuestos();
        }
    }

    @FXML
    public void AgregarRepuestos(){
        Registro r = admin.getRegistro();
        admin.Repuestos(r.getIdregistro(),r);
        admin.cerrarVentana("");
    }
    public void mostrar_repuestos(){
        ColCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        ColRepuesto.setCellValueFactory(new PropertyValueFactory<>("Repuesto"));
        ColValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        
        String query = "SELECT * FROM descripcion WHERE idregistro = "+registro.getIdregistro()+";";
        Query consulta = em.createNativeQuery(query, Descripcion.class);
        List<Descripcion> d = consulta.getResultList();
        
        TablaRepuestos.setItems(FXCollections.observableArrayList(d));
    }
    @FXML
    void GenerarBackupMySQL(){
        Runtime app = Runtime.getRuntime();
        
        try {
            
            app.exec("C:\\Program Files\\PostgreSQL\\9.5\\bin\\pgAdmin3.exe");
            
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    public BigInteger costoTotal(){
        
        String query = "SELECT SUM(valor) FROM descripcion WHERE idregistro = "+registro.getIdregistro()+";";
        Query consulta = em.createNativeQuery(query);
        BigInteger costoTotal = new BigInteger("0");
        
        if(consulta.getResultList().isEmpty()){
            costoTotal = new BigInteger("0");
        } else{
            costoTotal = new BigInteger(consulta.getSingleResult().toString());
        }
        
        return costoTotal;
    }
}


