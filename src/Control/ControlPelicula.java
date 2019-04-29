/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import modelo.Pelicula;

/**
 *
 * @author admin-sala3
 */
public class ControlPelicula {

    public boolean modificarPelicula(Pelicula objp, int id) {
        boolean t = false;
        try {
            Date a = new Date();

            Timestamp ti = new Timestamp(a.getTime());

            String year, OL, L, des,rat,sf;

            if (objp.getRelease_year() == 0) {
                year = null;
            } else {
                year = objp.getRelease_year() + "";
            }
            if (objp.getOriginal_language() == 0) {
                OL = null;
            } else {
                OL = objp.getOriginal_language() + "";
            }
            if (objp.getLength() == 0) {
                L = null;
            } else {
                L = objp.getLength() + "";
            }
            if (objp.getDescription() == null) {
                des = null;
            } else {
                des = "'" + objp.getDescription() + "'";
            }
            if (objp.getRating()== null) {
                rat = null;
            } else {
                rat = "'" + objp.getRating() + "'";
            }
            if (objp.getSpecial_features()== null) {
                sf = null;
            } else {
                sf = "'" + objp.getSpecial_features() + "'";
            }

            String sql = "update film set title = '" + objp.getTitle() + "', description = " + des + " , release_year =" + year + ", "
                    + "language_id =" + objp.getLanguage_id() + ", original_language_id = " + OL + ", rental_duration = " + objp.getRental_duration() + ", "
                    + "rental_rate = " + objp.getRental_rate() + ", length = " + L + ", replacement_cost = " + objp.getReplacement_cost() + ", rating = " + rat + ", "
                    + "special_features = " + sf + ", last_update = '" + ti + "' WHERE film_id =" + id;

            t = objp.modPelicula(sql);

        } catch (Exception e) {
            System.out.println("ERRORes  " + e);
        }
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
//                    System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getInt(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getDouble(8)
//                            + rs.getInt(9) + " " + rs.getDouble(10) + rs.getString(11) + " " + rs.getString(12) + rs.getTimestamp(13));

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
