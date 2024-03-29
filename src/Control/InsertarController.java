/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.Pelicula;

/**
 * FXML Controller class
 *
 * @author admin-sala3
 */
public class InsertarController implements Initializable {

    @FXML
    TextField Txtitulo;
    @FXML
    TextField Txdescripcion;
    @FXML
    TextField Txanio;
    @FXML
    TextField Txlenguaje;
    @FXML
    TextField Txlenguajeorg;
    @FXML
    TextField Txrentalduration;
    @FXML
    TextField Txrentalrate;
    @FXML
    TextField Txduration;
    @FXML
    TextField Txcosto;
    @FXML
    TextField Txrestriccion;
    @FXML
    TextField Txcontent;
    @FXML
    TextField Txhora;
    @FXML
    DatePicker Txfecha;

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
        try {
            String description = null;
            int release_year = 0;
            int original_language_id = 0;
            int length = 0;
            String rating = null;
            String special_features = null;

            if (!"".equals(Txdescripcion.getText())) {
                description = Txdescripcion.getText();
            }
            if (!"".equals(Txrestriccion.getText())) {
                rating = Txrestriccion.getText();
            }
            if (!"".equals(Txcontent.getText())) {
                special_features = Txcontent.getText();
            }
            if (!"".equals(Txanio.getText())) {
                release_year = Integer.parseInt(Txanio.getText());
            }
            if (!"".equals(Txlenguajeorg.getText())) {
                original_language_id = Integer.parseInt(Txlenguajeorg.getText());
            }
            if (!"".equals(Txduration.getText())) {
                length = Integer.parseInt(Txduration.getText());
            }

            String fech = Txfecha.getValue().toString();
            String hour = Txhora.getText();

            String sp1[] = fech.split("-");

            for (int i = 0; i < sp1.length; i++) {

                System.out.println(sp1[i]);

            }

            String sp2[] = hour.split(":");

            for (int i = 0; i < sp2.length; i++) {

                System.out.println(sp2[i]);

            }

            Timestamp time = new Timestamp(Integer.parseInt(sp1[0]) - 1900, Integer.parseInt(sp1[1]) - 1,
                    Integer.parseInt(sp1[2]), Integer.parseInt(sp2[0]),
                    Integer.parseInt(sp2[1]), Integer.parseInt(sp2[2]), 0);

            //se crea un objeto de controlPelicula con constructor vacio
            ControlPelicula objCP = new ControlPelicula();
            //Se crea una Pelicula nula
            Pelicula objP = null;

            boolean ins = false;

            //Se llena el objeto de cuetna con todos sus atributos
            objP = new Pelicula(Txtitulo.getText(), description, release_year, Integer.parseInt(Txlenguaje.getText()),
                    original_language_id, Integer.parseInt(Txrentalduration.getText()), Double.parseDouble(Txrentalrate.getText()),
                    length, Double.parseDouble(Txcosto.getText()), rating, special_features, time);

            //Se llama al metodo de controlcuenta para insertar
            ins = objCP.insertarPelicula(objP);

            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/Vista/FXMLDocument.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            //stage.setTitle("Login");

            stage.setScene(scene);

            stage.show();
        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
            JOptionPane.showMessageDialog(null, "Datos Erroneos Por Favor Verifique", "Error al insertar Pelicula", JOptionPane.ERROR_MESSAGE, null);
        }

    }

}
