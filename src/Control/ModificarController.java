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
import javax.swing.JOptionPane;
import modelo.Pelicula;

/**
 * FXML Controller class
 *
 * @author admin-sala3
 */
public class ModificarController implements Initializable {
    
    @FXML
    TextField modify_title;
    @FXML
    TextField modify_id;
    @FXML
    TextField modify_descrip;
    @FXML
    TextField modify_anolanzamiento;
    @FXML
    TextField modify_lenguaje;
    @FXML
    TextField modify_lenguajeoriginal;
    @FXML
    TextField modify_rentalduration;
    @FXML
    TextField modify_rentalrate;
    @FXML
    TextField modify_duracion;
    @FXML
    TextField modify_costoremplazo;
    @FXML
    TextField modify_restriccion;
    @FXML
    TextField modify_contenidoextra;
    @FXML
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
            ControlPelicula objCP = new ControlPelicula();
            
            int id = Integer.parseInt(modify_id.getText());
            
            Pelicula objP = objCP.consultModPelicula(id);
            
            modify_title.setText(objP.getTitle());
            modify_descrip.setText(objP.getDescription());
            modify_anolanzamiento.setText(objP.getRelease_year() + "");
            modify_lenguaje.setText(objP.getLanguage_id() + "");
            modify_lenguajeoriginal.setText(objP.getOriginal_language() + "");
            modify_rentalduration.setText(objP.getRental_duration() + "");
            modify_rentalrate.setText(objP.getRental_rate() + "");
            modify_duracion.setText(objP.getLength() + "");
            modify_costoremplazo.setText(objP.getReplacement_cost() + "");
            modify_restriccion.setText(objP.getRating());
            modify_contenidoextra.setText(objP.getSpecial_features());
            
            modify_title.setDisable(false);
            modify_descrip.setDisable(false);
            modify_anolanzamiento.setDisable(false);
            modify_lenguaje.setDisable(false);
            modify_lenguajeoriginal.setDisable(false);
            modify_rentalduration.setDisable(false);
            modify_rentalrate.setDisable(false);
            modify_duracion.setDisable(false);
            modify_costoremplazo.setDisable(false);
            modify_restriccion.setDisable(false);
            modify_contenidoextra.setDisable(false);
            
            //modify_ultimamodif.setDisable(false);

            //modify_ultimamodif.setText(objP.()+"");
            //ControlPelicula objcp=new ControlPelicula();
//        Pelicula objp = new Pelicula(modify_title.getText(), modify_descrip.getText(), Integer.parseInt(modify_anolanzamiento.getText()), 
//                Integer.parseInt(modify_lenguaje.getText()), Integer.parseInt(modify_lenguajeoriginal.getText()), 
//                Integer.parseInt(modify_rentalduration.getText()), Double.parseDouble(modify_rentalrate.getText()), 
//                Integer.parseInt(modify_duracion.getText()),Double.parseDouble(modify_costoremplazo.getText()),
//                modify_restriccion.getText(),modify_contenidoextra.getText(),(Timestamp) modify_ultimamodif.getText());
//        boolean f=objcp.modificarPelicula(objp);
//        if(f){
//            
//            System.out.println("Se modifico la pelicula");
//        }else{
//            System.out.println("No modifico la pelicula");
//        } 
        } catch (Exception e) {
            System.out.println("ERROR" + e);
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Datos Erroneos Por Favor Verifique", "Error buscar Pelicula", JOptionPane.ERROR_MESSAGE, null);
        }
        
    }
}
