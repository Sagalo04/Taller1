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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.Pelicula;
import modelo.Persona;

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
    @FXML
    TableView tableView;

    int cont;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cont = 0;
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

        String nombre = "", apellido = "";
        ConnectBD con = new ConnectBD();
        String sql = null, sql2 = null;

        if (cont > 0) {
            tableView.getColumns().removeAll(tableView.getColumns());
            tableView.getItems().removeAll(tableView.getItems());
        }

        //-----------
        if (nomCliente != "") {
            cont = 1;

            TableColumn<String, Persona> column3 = new TableColumn<>("Pelicula");
            column3.setCellValueFactory(new PropertyValueFactory<>("pelicula"));

            tableView.getColumns().add(column3);

            //SQL para las peliculas que ha rentado un cliente
            sql = "SELECT title\n"
                    + "FROM sakila.film PI INNER JOIN sakila.inventory I ON PI.film_id = I.film_id \n"
                    + "INNER JOIN sakila.rental R ON I.inventory_id = R.inventory_id LEFT JOIN sakila.customer C ON  R.customer_id= C.customer_id where C.customer_id =" + idCliente;
            /*"SELECT film.title as Pelicula\n" +
                    "FROM rental \n" +
                    "INNER JOIN customer INNER JOIN film\n" +
                    "WHERE rental.customer_id=customer.customer_id AND customer.first_name =" + nomCliente;*/
            //System.out.println(nomCliente);      
            sql2 = "SELECT first_name,last_name FROM sakila.customer where customer_id =" + idCliente;

            //--------
            ResultSet rs = null;
            ResultSet rs2 = null;
            String r = "";

            if (con.crearConexion()) {
                try {

                    Statement s = con.getConexion().createStatement();
                    rs = s.executeQuery(sql);
                    while (rs.next()) {
                        TituloPeli = rs.getString(1);
                        System.out.println(TituloPeli + "\n");
                        //txAReporte.setText(rs.getString("title"));
                        tableView.getItems().add(new Persona("", "", TituloPeli));
                    }
                    rs2 = s.executeQuery(sql2);

                    while (rs2.next()) {

                        nombre = rs2.getString(1);
                        apellido = rs2.getString(2);
                        System.out.println(nombre + " " + apellido);
                        //txAReporte.setText(rs.getString("title"));

                    }

                    TableColumn<String, Persona> column2 = new TableColumn<>("Cliente: " + nombre + " " + apellido + " ");
                    //column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                    tableView.getColumns().add(0, column2);

                    con.getConexion().close();

                } catch (SQLException e) {

                    System.out.println("ERROR CONSULTA " + e.toString());
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Debe ingresar los datos correctamente", "Error al hacer busqueda", JOptionPane.ERROR_MESSAGE, null);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar los datos correctamente", "Error al hacer busqueda", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @FXML
    private void onBuscar1(ActionEvent event) {
        String nombre = "", apellido = "";

        if (cont > 0) {
            tableView.getColumns().removeAll(tableView.getColumns());
            tableView.getItems().removeAll(tableView.getItems());
        }

        String nomActor = txActorC.getText();
        String TituloPeli = "";

        ConnectBD con = new ConnectBD();
        String sql = null, sql2 = null;

        if (nomActor != "") {
            TableColumn<String, Persona> column3 = new TableColumn<>("Pelicula");
            column3.setCellValueFactory(new PropertyValueFactory<>("pelicula"));

            tableView.getColumns().add(column3);

            cont = 1;
            sql = "SELECT f.title\n"
                    + "FROM  film f\n"
                    + "JOIN film_actor fa \n"
                    + "ON  f.film_id = fa.film_id\n"
                    + "WHERE fa.actor_id IN ( \n"
                    + "Select actor_id \n"
                    + "FROM actor \n"
                    + "WHERE actor_id = " + nomActor + ")";
            //"WHERE concat(actor.first_name, \" \", actor.last_name) =" + nomActor;
            sql2 = "Select first_name,last_name from actor where actor_id =" + nomActor;
            System.out.println(nomActor);

            ResultSet rs = null;
            ResultSet rs2 = null;
            String r = "";

            if (con.crearConexion()) {
                try {

                    Statement s = con.getConexion().createStatement();
                    rs = s.executeQuery(sql);

                    while (rs.next()) {

                        TituloPeli = rs.getString(1);
                        //System.out.println(TituloPeli + "\n");
                        //txAReporte.setText(rs.getString("title"));
                        tableView.getItems().add(new Persona("", "", TituloPeli));
                    }
                    rs2 = s.executeQuery(sql2);

                    while (rs2.next()) {

                        nombre = rs2.getString(1);
                        apellido = rs2.getString(2);
                        System.out.println(nombre + " " + apellido);
                        //txAReporte.setText(rs.getString("title"));

                    }
                    TableColumn<String, Persona> column2 = new TableColumn<>("Actor: " + nombre + " " + apellido + " ");
                    //column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                    tableView.getColumns().add(0, column2);

                    con.getConexion().close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("ERROR CONSULTA " + e.toString());
                    JOptionPane.showMessageDialog(null, "Debe ingresar los datos correctamente", "Error al hacer busqueda", JOptionPane.ERROR_MESSAGE, null);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar los datos correctamente", "Error al hacer busqueda", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @FXML
    private void onBuscar2(ActionEvent event) {
        //Buscar de género
        if (cont > 0) {
            tableView.getColumns().removeAll(tableView.getColumns());
            tableView.getItems().removeAll(tableView.getItems());
        }

        TableColumn<String, Persona> column3 = new TableColumn<>("Pelicula");
        column3.setCellValueFactory(new PropertyValueFactory<>("pelicula"));

        tableView.getColumns().add(column3);

        cont = 1;

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
                    tableView.getItems().add(new Persona("", "", TituloPeli));
                }

                con.getConexion().close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR CONSULTA " + e.toString());
                JOptionPane.showMessageDialog(null, "Error Inesperado", "Error al hacer busqueda", JOptionPane.ERROR_MESSAGE, null);
            }
        }
    }

    @FXML
    private void onBuscar3(ActionEvent event) {

        String fechaIni = txFechaInicial.getText();
        String fechaFinal = txFechaFinal.getText();
        String TituloPeli = "";
        String nombre = "", apellido = "";

        ConnectBD con = new ConnectBD();
        String sql = null;

        if (fechaIni != null && fechaFinal != null && datepicker.getValue() != null && datepicker2.getValue() != null) {

            if (cont > 0) {
                tableView.getColumns().removeAll(tableView.getColumns());
                tableView.getItems().removeAll(tableView.getItems());
            }

            cont = 1;
            TableColumn<String, Persona> column1 = new TableColumn<>("First Name");
            column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<String, Persona> column2 = new TableColumn<>("Last Name");
            column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<String, Persona> column3 = new TableColumn<>("Id Renta");
            column3.setCellValueFactory(new PropertyValueFactory<>("rentalid"));

            tableView.getColumns().add(column1);
            tableView.getColumns().add(column2);
            tableView.getColumns().add(column3);

            //SQL para  Todas las rentas realizadas en un periodo de tiempo.
            /* OJO = No me sale el calendario ._. no puedo llamarlo */
            String fechinicio = datepicker.getValue().toString() + fechaIni;

            String fechfinal = datepicker2.getValue().toString() + fechaFinal;

//
//            Timestamp time = new Timestamp(Integer.parseInt(sp1[0]) - 1900, Integer.parseInt(sp1[1]) - 1,
//                    Integer.parseInt(sp1[2]), Integer.parseInt(sp2[0]),
//                    Integer.parseInt(sp2[1]), Integer.parseInt(sp2[2]), 0);
            sql = "select customer.first_name,customer.last_name, rental.rental_id as 'id renta'  "
                    + "from customer inner join rental on customer.customer_id = rental.customer_id where rental.rental_date between '" + fechinicio + "' and '" + fechfinal + "'";

            ResultSet rs = null;
            String r = "";

            if (con.crearConexion()) {
                try {

                    Statement s = con.getConexion().createStatement();
                    rs = s.executeQuery(sql);

                    while (rs.next()) {

                        nombre = rs.getString(1);
                        apellido = rs.getString(2);
                        int ida = rs.getInt(3);
                        //txAReporte.setText(rs.getString("title"));

                        tableView.getItems().add(new Persona(nombre, apellido, ida));
                    }

                    con.getConexion().close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("ERROR CONSULTA " + e.toString());
                    JOptionPane.showMessageDialog(null, "Debe ingresar los datos correctamente", "Error al hacer busqueda", JOptionPane.ERROR_MESSAGE, null);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos", "Error al hacer busqueda", JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
