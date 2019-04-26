/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.net.URL;
import java.sql.Timestamp;
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
public class InsertarController implements Initializable {

    @FXML
    TextField Txtitulo;
    TextField Txdescripcion;
    TextField Txanio;
    TextField Txlenguaje;
    TextField Txlenguajeorg;
    TextField Txrentalduration;
    TextField Txrentalrate;
    TextField Txduration;
    TextField Txcosto;
    TextField Txrestriccion;
    TextField Txcontent;
    TextField Txupdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onCancelar(ActionEvent event) {
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
    private void onContinuar(ActionEvent event) {
        
                //se crea un objeto de controlPelicula con constructor vacio
        ControlPelicula objPC = new ControlPelicula();
        //Se crea una Pelicula nula
            Pelicula objP = null;

        boolean ins = false;

        //Se llena el objeto de cuetna con todos sus atributos
        try {
            //objP = new Pelicula(Txtitulo.getText(), Txdescripcion.getText(), Integer.parseInt(Txanio.getText()), Integer.parseInt(Txlenguaje.getText()), 
              //      Integer.parseInt(Txlenguajeorg.getText()), Integer.parseInt(Txrentalduration.getText()), Double.parseDouble(Txrentalrate.getText()),
                //    Integer.parseInt(Txduration.getText()),Double.parseDouble(Txcosto.getText()),Txrestriccion.getText(),Txcontent.getText(),(Timestamp) Txupdate.getText());

            //Se llama al metodo de controlcuenta para insertar
            //ins = objCP.insertarCuenta(objP);

        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
        }
        
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

}
