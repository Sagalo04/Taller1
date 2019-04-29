/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.Pelicula;

/**
 * FXML Controller class
 *
 * @author admin-sala3
 */
public class ConsultaController implements Initializable {

    @FXML
    TextField txClienteC;
    @FXML
    TextField txActorC;
    @FXML
    TextField txFechaInicial;
    @FXML
    TextField txFechaFinal;
    @FXML
    TextArea txAReporte;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btCancelar(ActionEvent event) {
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
    private void onBuscar(ActionEvent event) {
       
        String idCliente = txClienteC.getText();
        String TituloPeli = "";
        
        ConnectBD con = new ConnectBD();
        String sql = "SELECT title\n" +
        "FROM sakila.film PI INNER JOIN sakila.inventory I ON PI.film_id = I.film_id \n" +
        "INNER JOIN sakila.rental R ON I.inventory_id = R.inventory_id LEFT JOIN sakila.customer C ON  R.customer_id= C.customer_id where C.customer_id =" + idCliente;

        ResultSet rs = null;

        String r = "";

        
        if (con.crearConexion()) {
            try {

                Statement s = con.getConexion().createStatement();
                rs = s.executeQuery(sql);

                while (rs.next()) {
                   
                  TituloPeli = rs.getString("title");
                  System.out.println(TituloPeli + "\n");
                  //txAReporte.setText(rs.getString("title"));

                }

                con.getConexion().close();

            } catch (SQLException e) {
                System.out.println("ERROR CONSULTA " + e.toString());
            }
        }
    }

}
