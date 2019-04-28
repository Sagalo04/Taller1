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

    public boolean modificarPelicula(Pelicula objp,int id) {
        
        Date a = new Date();
        
        Timestamp ti = new Timestamp(a.getTime());
        
        boolean t = false;
        String sql = "update film set title = '"+ objp.getTitle() +"', description = '"+objp.getDescription()+"' , release_year ="+objp.getRelease_year()+", "
                + "language_id ="+ objp.getLanguage_id() +", original_language_id = "+objp.getOriginal_language()+", rental_duration = "+objp.getRental_duration()+", "
                + "rental_rate = "+objp.getRental_rate()+", length = "+objp.getLength()+", replacement_cost = "+objp.getReplacement_cost()+", rating = '"+objp.getRating()+"', "
                + "special_features = '"+objp.getSpecial_features()+"', last_update = '"+ti+"' WHERE film_id =" + id;

        t = objp.modPelicula(sql);

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
