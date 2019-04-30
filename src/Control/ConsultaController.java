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
import java.sql.Timestamp;
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
    DatePicker datepicker;
    @FXML
    DatePicker datepicker2;

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

        if (nomCliente != null) {
            //SQL para las peliculas que ha rentado un cliente
            sql = "SELECT title\n"
                    + "FROM sakila.film PI INNER JOIN sakila.inventory I ON PI.film_id = I.film_id \n"
                    + "INNER JOIN sakila.rental R ON I.inventory_id = R.inventory_id LEFT JOIN sakila.customer C ON  R.customer_id= C.customer_id where C.customer_id =" + idCliente;
            /*"SELECT film.title as Pelicula\n" +
                    "FROM rental \n" +
                    "INNER JOIN customer INNER JOIN film\n" +
                    "WHERE rental.customer_id=customer.customer_id AND customer.first_name =" + nomCliente;*/
            //System.out.println(nomCliente);      
        }

        //--------
        ResultSet rs = null;
        String r = "";

        if (con.crearConexion()) {
            try {

                Statement s = con.getConexion().createStatement();
                rs = s.executeQuery(sql);

                while (rs.next()) {

                    TituloPeli = rs.getString(1);
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
        String TituloPeli = "";

        ConnectBD con = new ConnectBD();
        String sql = null;

        if (nomActor != null) {
            sql = "SELECT f.title\n"
                    + "FROM  film f\n"
                    + "JOIN film_actor fa \n"
                    + "ON  f.film_id = fa.film_id\n"
                    + "WHERE fa.actor_id IN ( \n"
                    + "Select actor_id \n"
                    + "FROM actor \n"
                    + "WHERE actor_id = " + nomActor + ")";
            //"WHERE concat(actor.first_name, \" \", actor.last_name) =" + nomActor;
            System.out.println(nomActor);

            ResultSet rs = null;
            String r = "";

            if (con.crearConexion()) {
                try {

                    Statement s = con.getConexion().createStatement();
                    rs = s.executeQuery(sql);

                    while (rs.next()) {

                        TituloPeli = rs.getString(1);
                        System.out.println(TituloPeli + "\n");
                        //txAReporte.setText(rs.getString("title"));

                    }

                    con.getConexion().close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("ERROR CONSULTA " + e.toString());
                }
            }
        }
    }

    @FXML
    private void onBuscar2(ActionEvent event) {
        //Buscar de género

        String TituloPeli = "";

        ConnectBD con = new ConnectBD();
        String sql = null;

        sql = "SELECT f.title\n"
                + "FROM film f \n"
                + "INNER JOIN film_category fc ON f.film_id = fc.film_id\n"
                + "INNER JOIN category c ON fc.category_id = c.category_id\n"
                + //"WHERE c.name =" + genero ;
                "WHERE c.category_id = '11'";

        ResultSet rs = null;
        String r = "";

        if (con.crearConexion()) {
            try {

                Statement s = con.getConexion().createStatement();
                rs = s.executeQuery(sql);

                while (rs.next()) {

                    TituloPeli = rs.getString(1);
                    System.out.println(TituloPeli + "\n");
                    //txAReporte.setText(rs.getString("title"));

                }

                con.getConexion().close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR CONSULTA " + e.toString());
            }
        }
    }

    @FXML
    private void onBuscar3(ActionEvent event) {

        String fechaIni = txFechaInicial.getText();
        String fechaFinal = txFechaFinal.getText();
        String TituloPeli = "";

        ConnectBD con = new ConnectBD();
        String sql = null;

        if (fechaIni != null && fechaFinal != null && datepicker.getValue() != null && datepicker2.getValue() != null) {
            //SQL para  Todas las rentas realizadas en un periodo de tiempo.
            /* OJO = No me sale el calendario ._. no puedo llamarlo */
            String fechinicio = datepicker.getValue().toString();
            String hourinicio = fechaIni;

            String fechfinal = datepicker2.getValue().toString();
            String hourfinal = fechaFinal;

            System.out.println(fechinicio + " "+hourinicio);
            System.out.println(fechfinal + " "+hourfinal);
            String spfinicio[] = fechinicio.split("-");
            for (int i = 0; i < spfinicio.length; i++) {
                //System.out.println(spfinicio[i]);
            }

            String sphinicio[] = hourinicio.split(":");
            for (int i = 0; i < sphinicio.length; i++) {
                //System.out.println(sphinicio[i]);

            }
            System.out.println("\n FECHA FINAL");
            String spffinal[] = fechfinal.split("-");
            for (int i = 0; i < spffinal.length; i++) {
                //System.out.println(spffinal[i]);
            }

            String sphfinal[] = hourfinal.split(":");
            for (int i = 0; i < sphfinal.length; i++) {
                //System.out.println(sphfinal[i]);
            }
//
//            Timestamp time = new Timestamp(Integer.parseInt(sp1[0]) - 1900, Integer.parseInt(sp1[1]) - 1,
//                    Integer.parseInt(sp1[2]), Integer.parseInt(sp2[0]),
//                    Integer.parseInt(sp2[1]), Integer.parseInt(sp2[2]), 0);

            sql = "select concat(customer.first_name,' ',customer.last_name) as 'nombre', rental.rental_id as 'id renta'  "
                    + "from customer inner join rental on customer.customer_id = rental.customer_id where rental.rental_date between '2005-05-25 00:19:27' and '2005-05-25 06:44:53'";

            ResultSet rs = null;
            String r = "";

            if (con.crearConexion()) {
                try {

                    Statement s = con.getConexion().createStatement();
                    rs = s.executeQuery(sql);

                    while (rs.next()) {

                        TituloPeli = rs.getString(1);
                        int ida = rs.getInt(2);
                        System.out.println(TituloPeli +" "+ ida);
                        //txAReporte.setText(rs.getString("title"));

                    }

                    con.getConexion().close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("ERROR CONSULTA " + e.toString());
                }
            }
        }
    }

}
