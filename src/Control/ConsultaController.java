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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
    @FXML
    MenuButton  mnGenero;
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
        String nomCliente = txClienteC.getText();
        String TituloPeli = "";
        
        ConnectBD con = new ConnectBD();
        String sql = null;
        /*String sql = "SELECT title\n" +
        "FROM sakila.film PI I NNER JOIN sakila.inventory I ON PI.film_id = I.film_id \n" +
        "INNER JOIN sakila.rental R ON I.inventory_id = R.inventory_id LEFT JOIN sakila.customer C ON  R.customer_id= C.customer_id where C.customer_id =" + idCliente;

        ResultSet rs = null;

        String r = "";*/
        //-----------
        
        if (nomCliente != null){
              //SQL para las peliculas que ha rentado un cliente
            sql = "SELECT film.title as Pelicula\n" +
                    "FROM rental \n" +
                    "INNER JOIN customer INNER JOIN film\n" +
                    "WHERE rental.customer_id=customer.customer_id AND customer.first_name =" + nomCliente;
            System.out.println(nomCliente);      
        }
        
        //--------
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
    
    @FXML
    private void onBuscar1(ActionEvent event) {
        
        String nomActor = txActorC.getText();
        
        ConnectBD con = new ConnectBD();
        String sql = null;
        
        if(nomActor != null){
            sql = "SELECT f.title\n" +
                    "FROM  film f\n" +
                    "JOIN film_actor fa \n" +
                    "ON  f.film_id = fa.film_id\n" +
                    "WHERE fa.actor_id IN ( \n" +
                    "Select actor_id \n" +
                    "FROM actor \n" +
                    "WHERE concat(actor.first_name, \" \", actor.last_name) =" + nomActor;
            System.out.println(nomActor);
            
        ResultSet rs = null;
        String r = "";
        }
    }
    
    @FXML
    private void onBuscar2(ActionEvent event) {
        //Buscar de género
        
        String genero = mnGenero.getText();
        
        ConnectBD con = new ConnectBD();
        String sql = null;
        
        if(genero != null){
                
            sql = "SELECT f.title\n" +
                    "FROM film f \n" +
                    "INNER JOIN film_category fc ON f.film_id = fc.film_id\n" +
                    "INNER JOIN category c ON fc.category_id = c.category_id\n" +
                    //"WHERE c.name =" + genero ;
                    "WHERE c.name = 'Horror'" ;  
            
        ResultSet rs = null;
        String r = "";
        }
    }
    
    @FXML
    private void onBuscar3(ActionEvent event) {
        
        String fechaIni = txFechaInicial.getText();
        String fechaFinal = txFechaFinal.getText();
        
        ConnectBD con = new ConnectBD();
        String sql = null;
        
        if(fechaIni != null && fechaFinal != null){
            //SQL para  Todas las rentas realizadas en un periodo de tiempo.
            /* OJO = No me sale el calendario ._. no puedo llamarlo */
            sql =  "SELECT film.title as Pelicula,rental.rental_id as NumeroRenta, concat(customer.first_name,\" \",customer.last_name) as cliente ,rental.rental_date as FechaInicial,rental.return_date as FechaFinal \n"+
                    "FROM rental \n"+
                    "INNER JOIN customer INNER JOIN film \n"+
                    "WHERE rental.customer_id=customer.customer_id AND rental.rental_date BETWEEN BETWEEN '2005-05-25 00:19:27 ' AND '2005-05-25 06:44:53'";     
            
        ResultSet rs = null;
        String r = "";
        }
    }

}
