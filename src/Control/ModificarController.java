/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Pelicula;

/**
 * FXML Controller class
 *
 * @author admin-sala3
 */
public class ModificarController implements Initializable {

    @FXML
    TextField modify_title;
    TextField modify_id;
    TextField modify_descrip;
    TextField modify_anolanzamiento;
    TextField modify_lenguaje;
    TextField modify_lenguajeoriginal;
    TextField modify_rentalduration;
    TextField modify_rentalrate;
    TextField modify_duracion;
    TextField modify_costoremplazo;
    TextField modify_restriccion;
    TextField modify_contenidoextra;
    TextField modify_ultimamodif;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    } 
    
        @FXML 
    private void onCancel(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/FXMLDocument.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            //stage.setTitle("Login");

            stage.setScene(scene);

            stage.show();

        } catch (Exception e) {
        }

    }
    
        @FXML 
    private void onModificar(ActionEvent event) {
        
        try {
        ControlPelicula objcp=new ControlPelicula();
        Pelicula objp = new Pelicula(modify_title.getText(), modify_descrip.getText(), Integer.parseInt(modify_anolanzamiento.getText()), Integer.parseInt(modify_lenguaje.getText()), Integer.parseInt(modify_lenguajeoriginal.getText()), Integer.parseInt(modify_rentalduration.getText()), Double.parseDouble(modify_rentalrate.getText()), Integer.parseInt(modify_duracion.getText()),Double.parseDouble(modify_costoremplazo.getText()),modify_restriccion.getText(),modify_contenidoextra.getText(),(Timestamp) modify_ultimamodif.getText());
        
        boolean f=objcp.modificarPelicula(objp);
        
        if(f){
            
            System.out.println("Se modifico la pelicula");
        }else{
            System.out.println("No modifico la pelicula");
        } 

        } catch (Exception e) {
        }

    }
}
