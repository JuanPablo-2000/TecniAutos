/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGICA;

import BD.Descripcion;
import BD.Registro;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.text.DecimalFormat;
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
    @FXML
    private TextField direccion;
    @FXML
    private TextField telefono;
    @FXML
    private ImageView pdf;
    @FXML
    private Label Etiquetapdf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fecha.setFocusTraversable(true);
        costo.setDisable(true);
        em = Persistence.createEntityManagerFactory("TecniAutosPU").createEntityManager();

        TecniAutos.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.TAB) {
                fecha.requestFocus();

            }
        });

        TecniAutos.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
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

    public TextField getDireccion() {
        return direccion;
    }

    public void setDireccion(TextField direccion) {
        this.direccion = direccion;
    }

    public TextField getTelefono() {
        return telefono;
    }

    public void setTelefono(TextField telefono) {
        this.telefono = telefono;
    }

    @FXML
    public void Crear() {

        if (kilometraje.getText().equalsIgnoreCase("") || placaVehiculo.getText().equalsIgnoreCase("")
                || marcaVehiculo.getText().equalsIgnoreCase("") || fecha.toString().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Llene los Campos Correctamente...");

        } else {
            Date fechaParseo = java.sql.Date.valueOf(fecha.getValue());
            BigInteger costoparseo = new BigInteger("0");

            if (!costo.getText().equalsIgnoreCase("")) {
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
            registro.setDireccion(direccion.getText());
            registro.setTelefono(telefono.getText());

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
    public void Modificar() {
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
        registro.setDireccion(direccion.getText());
        registro.setTelefono(telefono.getText());
        
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
        registro.setDireccion(direccion.getText());
        registro.setTelefono(telefono.getText());
    }

    @FXML
    public void Eliminar() {
        Date fechaParseo = java.sql.Date.valueOf(fecha.getValue());

        String query = "DELETE FROM registro WHERE fecha='" + fechaParseo + "' AND placa='" + placaVehiculo.getText().toUpperCase() + "';";
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
        direccion.setText("");
        telefono.setText("");

        JOptionPane.showMessageDialog(null, "Se Elimino Corectamente");
    }

    @FXML
    public void Buscar() {
        admin.Registro();
        admin.cerrarVentana("");
    }

    @FXML
    public void Cerrar() {
        admin.cerrarVentana("");
    }

    private static boolean isNumeric(String dato) {
        try {
            Integer.parseInt(dato);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void tomarBusqueda(int idRegistro) {

        if (idRegistro > 0) {
            String query = "SELECT * FROM registro WHERE idregistro=" + idRegistro + ";";
            Query consulta = em.createNativeQuery(query, Registro.class);
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

            if (r.get(0).getTelefono() == null
                    && r.get(0).getDireccion() == null) {
                telefono.setText("");
                direccion.setText("");
            } else {
                telefono.setText(r.get(0).getTelefono().toString());
                direccion.setText(r.get(0).getDireccion().toString());
            }

            modificar.setDisable(false);
            eliminar.setDisable(false);
            etiquetaModificar.setDisable(false);
            etiquetaEliminar.setDisable(false);
            AgregarRepuesto.setDisable(false);
            Agregar.setDisable(false);
            Repuesto.setDisable(false);
            pdf.setDisable(false);
            Etiquetapdf.setDisable(false);

            mostrar_repuestos();
        }
    }

    @FXML
    public void AgregarRepuestos() {
        Registro r = admin.getRegistro();
        admin.Repuestos(r.getIdregistro(), r);
        admin.cerrarVentana("");
    }

    public void mostrar_repuestos() {
        ColCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        ColRepuesto.setCellValueFactory(new PropertyValueFactory<>("Repuesto"));
        ColValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));

        String query = "SELECT * FROM descripcion WHERE idregistro = " + registro.getIdregistro() + ";";
        Query consulta = em.createNativeQuery(query, Descripcion.class);
        List<Descripcion> d = consulta.getResultList();

        TablaRepuestos.setItems(FXCollections.observableArrayList(d));
    }

    @FXML
    void GenerarBackupMySQL() {
        Runtime app = Runtime.getRuntime();

        try {

            app.exec("C:\\Program Files\\PostgreSQL\\9.5\\bin\\pgAdmin3.exe");

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public BigInteger costoTotal() {

        String query = "SELECT SUM(valor) FROM descripcion WHERE idregistro = " + registro.getIdregistro() + ";";
        Query consulta = em.createNativeQuery(query);
        BigInteger costoTotal = new BigInteger("0");

        if (consulta.getResultList().isEmpty()) {
            costoTotal = new BigInteger("0");
        } else {
            costoTotal = new BigInteger(consulta.getSingleResult().toString());
        }

        return costoTotal;
    }

    @FXML
    public void Pdf() {
        
        try{
            
            FileOutputStream archivo;
            File file = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\"+placaVehiculo.getText()+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            Image img = Image.getInstance("src\\IMG\\TecniAutos.png");
            
            DecimalFormat formatea = new DecimalFormat("###,###.###");
            
            Font negrita = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD, BaseColor.WHITE);
            Font parrafo = new Font(Font.FontFamily.UNDEFINED, 12, Font.NORMAL, BaseColor.BLACK);
            
            PdfPTable Encabezado = new PdfPTable(3);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{70f, 50f, 60f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            PdfPCell dato1 = new PdfPCell(new Phrase("Señor(a): "+ ucFirst(cliente.getText().toLowerCase()), parrafo));
            PdfPCell dato2 = new PdfPCell(new Phrase("C.C /Nit : "+ convertirDatos(cedula.getText()), parrafo));
            PdfPCell dato3 = new PdfPCell(new Phrase("Fecha : "+fecha.getValue().toString(), parrafo));
            
            dato1.setBorderColor(BaseColor.BLACK);
            dato2.setBorderColor(BaseColor.BLACK);
            dato3.setBorderColor(BaseColor.BLACK);
            
            dato1.setBorderWidthLeft(.5f);
            dato1.setBorderWidthRight(.5f);
            
            dato3.setBorderWidthLeft(.5f);
            dato3.setBorderWidthRight(.5f);
            
            dato1.setBorderWidthLeft(1f);
            dato1.setBorderWidthTop(1f);
            
            dato2.setBorderWidthTop(1f);
            
            dato3.setBorderWidthTop(1f);
            dato3.setBorderWidthRight(1f);
            
            Encabezado.addCell(dato1);
            Encabezado.addCell(dato2);
            Encabezado.addCell(dato3);
            
            PdfPTable Encabezado2 = new PdfPTable(3);
            Encabezado2.setWidthPercentage(100);
            Encabezado2.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado2 = new float[]{70f, 50f, 60f};
            Encabezado2.setWidths(ColumnaEncabezado2);
            Encabezado2.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            PdfPCell dato4 = new PdfPCell(new Phrase("Dirección : "+ucFirst(direccion.getText()), parrafo));
            PdfPCell dato5 = new PdfPCell(new Phrase("Empresa : ", parrafo));
            PdfPCell dato6 = new PdfPCell(new Phrase("Telefono : "+ convertirDatos(telefono.getText()), parrafo));
            
            dato4.setBorderColor(BaseColor.BLACK);
            dato5.setBorderColor(BaseColor.BLACK);
            dato6.setBorderColor(BaseColor.BLACK);
            
            dato4.setBorderWidthLeft(1f);
            dato6.setBorderWidthLeft(1f);
            dato6.setBorderWidthRight(1f);
            
            Encabezado2.addCell(dato4);
            Encabezado2.addCell(dato5);
            Encabezado2.addCell(dato6);
            
            PdfPTable Encabezado3 = new PdfPTable(3);
            Encabezado3.setWidthPercentage(100);
            Encabezado3.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado3 = new float[]{70f, 50f, 60f};
            Encabezado3.setWidths(ColumnaEncabezado3);
            Encabezado3.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            PdfPCell dato7 = new PdfPCell(new Phrase("Marca : "+ucFirst(marcaVehiculo.getText().toLowerCase()), parrafo));
            PdfPCell dato8 = new PdfPCell(new Phrase("Placa : "+placaVehiculo.getText(), parrafo));
            PdfPCell dato9 = new PdfPCell(new Phrase("Km : "+formatea.format(Double.parseDouble(convertirDatos(kilometraje.getText()))), parrafo));
            
            dato7.setBorderColor(BaseColor.BLACK);
            dato8.setBorderColor(BaseColor.BLACK);
            dato9.setBorderColor(BaseColor.BLACK);
            
            dato7.setBorderWidthBottom(1f);
            dato8.setBorderWidthBottom(1f);
            dato9.setBorderWidthBottom(1f);
            
            Encabezado3.addCell(dato7);
            Encabezado3.addCell(dato8);
            Encabezado3.addCell(dato9);
            
            //Repuestos
            Paragraph tabla = new Paragraph();
            tabla.add(Chunk.NEWLINE);
            tabla.add("");
            
            PdfPTable tablapro = new PdfPTable(3);
            tablapro.setWidthPercentage(100);
            float[] Columnapro = new float[] {25f, 90f, 20f};
            tablapro.setWidths(Columnapro);
            tablapro.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell pro1 = new PdfPCell(new Phrase("Cant. ", negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Descripción", negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Precio\n", negrita));
            
            pro1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro1.setVerticalAlignment(Element.ALIGN_CENTER);
            
            pro2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro2.setVerticalAlignment(Element.ALIGN_CENTER);
            
            pro3.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro3.setVerticalAlignment(Element.ALIGN_CENTER);
            
            pro1.setBackgroundColor(BaseColor.BLACK);
            pro2.setBackgroundColor(BaseColor.BLACK);
            pro3.setBackgroundColor(BaseColor.BLACK);
            
            tablapro.addCell(pro1);
            tablapro.addCell(pro2);
            tablapro.addCell(pro3);
            
            doc.add(Encabezado);
            doc.add(Encabezado2);
            doc.add(Encabezado3);
            doc.add(tabla);
            
            for(int i=0; i<TablaRepuestos.getItems().size(); i++){
                String cant = TablaRepuestos.getItems().get(i).getCantidad();
                String descr = TablaRepuestos.getItems().get(i).getRepuesto().toLowerCase();
                String prec = formatea.format(Double.valueOf(TablaRepuestos.getItems().get(i).getValor().toString()));
                
                PdfPCell cantidad = new PdfPCell(new Phrase(cant));
                PdfPCell descripcion = new PdfPCell(new Phrase(descr));
                PdfPCell precio = new PdfPCell(new Phrase(prec));
               
                cantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
                cantidad.setVerticalAlignment(Element.ALIGN_CENTER);
               
                precio.setHorizontalAlignment(Element.ALIGN_RIGHT);
                precio.setVerticalAlignment(Element.ALIGN_RIGHT);
                
                cantidad.setBorder(Rectangle.NO_BORDER);
                descripcion.setBorder(Rectangle.NO_BORDER);
                precio.setBorder(Rectangle.NO_BORDER);
                
                cantidad.setBorderWidthLeft(1f);
                precio.setBorderWidthRight(1f);
                
                if(TablaRepuestos.getItems().size()-1 == i){
                    cantidad.setBorderWidthBottom(1f);
                    descripcion.setBorderWidthBottom(1f);
                    precio.setBorderWidthBottom(1f);
                }
                
                tablapro.addCell(cantidad);
                tablapro.addCell(descripcion);
                tablapro.addCell(precio);
            }
            
            
            
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("\n\n\nFirma: ________________________________                                        "
                    + "Total a Pagar: "+ 
                    formatea.format(Double.valueOf(costo.getText())));
            info.setAlignment(Element.ALIGN_RIGHT);
            
            doc.add(tablapro);
            doc.add(info);
            doc.close();
            archivo.close();
            
            JOptionPane.showMessageDialog(null, "Se ha Generado Correctamente el Pdf.");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , "!!!  Error !!!");
        }
         
       
    }
    
    public static String ucFirst(String str) {
    if (str.isEmpty()) {
        return str;            
    } else {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1); 
    }
}
    
    public static String convertirDatos(String dato) {
        if (dato.contains("CEL")) {
            return dato.replace("CEL.", "");
        } else if (dato.contains("TEL")) {
            return dato.replace("TEL.", "");
        } else if (dato.contains("KM")) {
            return dato.replace("KM.", "");
        } else if (dato.contains("DIR")) {
            return dato.replace("DIR.", "");
        } else if (dato.contains("CC")) {
            return dato.replace("CC.", "");
        } else if (dato.contains("NIT")) {
            return dato.replace("NIT.", "");
        }
        
        return dato;
    }
}
