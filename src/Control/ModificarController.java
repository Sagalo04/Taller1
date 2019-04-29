/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
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

    int id;

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
    private void onAceptar(ActionEvent event) {

        String fh = modify_ultimamodif.getText();

        String splt[] = fh.split(" ");

        String sp1[] = splt[0].split("-");

        for (int i = 0; i < sp1.length; i++) {
            System.out.println(sp1[i]);
        }

        String sp2[] = splt[1].split(":");

        for (int i = 0; i < sp2.length; i++) {
            System.out.println(sp2[i]);
        }

        Date a = new Date();

        Timestamp ti = new Timestamp(a.getTime());

//               Timestamp time = new Timestamp(Integer.parseInt(sp1[0]) - 1900, Integer.parseInt(sp1[1]) - 1,
//               Integer.parseInt(sp1[2]), Integer.parseInt(sp2[0]),
//                Integer.parseInt(sp2[0]), Integer.parseInt(sp2[1]), 0);
        String description = null;
        int release_year = 0;
        int original_language_id = 0;
        int length = 0;
        String rating = null;
        String special_features = null;

        if (!"".equals(modify_descrip.getText())) {
            description = modify_descrip.getText();
        }
        if (!"".equals(modify_restriccion.getText())) {
            rating = modify_restriccion.getText();
        }
        if (!"".equals(modify_contenidoextra.getText())) {
            special_features = modify_contenidoextra.getText();
        }
        if (!"".equals(modify_anolanzamiento.getText())) {
            release_year = Integer.parseInt(modify_anolanzamiento.getText());
        }
        if (!"".equals(modify_lenguajeoriginal.getText())) {
            original_language_id = Integer.parseInt(modify_lenguajeoriginal.getText());
        }
        if (!"".equals(modify_duracion.getText())) {
            length = Integer.parseInt(modify_duracion.getText());
        }

        ControlPelicula objCP = new ControlPelicula();

        Pelicula objP = new Pelicula(modify_title.getText(), description, release_year, Integer.parseInt(modify_lenguaje.getText()),
                original_language_id, Integer.parseInt(modify_rentalduration.getText()), Double.parseDouble(modify_rentalrate.getText()),
                length, Double.parseDouble(modify_costoremplazo.getText()), rating, special_features, ti);

        try {
            boolean f = objCP.modificarPelicula(objP, id);
            if (f) {

                System.out.println("Se modifico la pelicula");
            } else {
                System.out.println("No modifico la pelicula");
            }

        } catch (Exception e) {
            System.out.println("ERROR CONT " + e.toString());
        }

    }

    @FXML
    private void onModificar(ActionEvent event) {

        try {
            ControlPelicula objCP = new ControlPelicula();

            id = Integer.parseInt(modify_id.getText());

            Pelicula objP = objCP.consultModPelicula(id);
            System.out.println(objP.getLast_update().toString());
            modify_title.setText(objP.getTitle());
            modify_descrip.setText(objP.getDescription());
            if (objP.getRelease_year() == 0) {
                modify_anolanzamiento.setText("");
            } else {
                modify_anolanzamiento.setText(objP.getRelease_year() + "");
            }
            modify_lenguaje.setText(objP.getLanguage_id() + "");
            if (objP.getOriginal_language() == 0) {
                modify_lenguajeoriginal.setText("");
            } else {
                modify_lenguajeoriginal.setText(objP.getOriginal_language() + "");
            }
            modify_rentalduration.setText(objP.getRental_duration() + "");
            modify_rentalrate.setText(objP.getRental_rate() + "");
            if (objP.getLength()==0) {
                modify_duracion.setText("");
            } else {
                modify_duracion.setText(objP.getLength() + "");
            }
            modify_costoremplazo.setText(objP.getReplacement_cost() + "");
            modify_restriccion.setText(objP.getRating());
            modify_contenidoextra.setText(objP.getSpecial_features());
            modify_ultimamodif.setText(objP.getLast_update().toString());

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

        } catch (Exception e) {
            System.out.println("ERROR" + e);
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Datos Erroneos Por Favor Verifique", "Error buscar Pelicula", JOptionPane.ERROR_MESSAGE, null);
        }

    }
}
