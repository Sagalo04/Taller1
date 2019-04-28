/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Pelicula;

/**
 *
 * @author admin-sala3
 */
public class ControlPelicula {

    public boolean modificarPelicula(Pelicula objp) {
        boolean t = false;
        String sql = "update film set title = ?, description = ?, release_year = ?, "
                + "language_id = ?, original_language_id = ?, rental_duration = ?, "
                + "rental_rate = ?, lenght = ?, repacement_cost = ?, rating = ?, "
                + "special_features = ?, last_update = ? where film_id =" + objp.getFilm_id();

        t = objp.modpelicula(sql, objp);

        return t;
    }

    boolean insertarPelicula(Pelicula objP) {
        String sql = "{ call añadir_movie(?,?,?,?,?,?,?,?,?,?,?,?) }";

        boolean f = false;

        f = objP.insertarPelicula(sql, objP);

        return f;

    }

    Pelicula consultModPelicula(int id) {

        ConnectBD con = new ConnectBD();
        String sql = "Select*from film where film_id= " + id;

        ResultSet rs = null;

        String r = "";

        Pelicula objP = null;

        if (con.crearConexion()) {
            try {

                Statement s = con.getConexion().createStatement();
                rs = s.executeQuery(sql);

                while (rs.next()) {
                    System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getInt(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getDouble(8)
                            + rs.getInt(9) + " " + rs.getDouble(10) + rs.getString(11) + " " + rs.getString(12) + rs.getTimestamp(13));

                    objP = new Pelicula(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getDouble(8), rs.getInt(9), rs.getDouble(10),
                             rs.getString(11), rs.getString(12), rs.getTimestamp(13));

                }

                con.getConexion().close();

            } catch (SQLException e) {
                System.out.println("ERROR CONTRLOP  " + e.toString());
            }
        }

        return objP;

    }
}
