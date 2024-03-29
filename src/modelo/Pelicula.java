/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Control.ConnectBD;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author admin-sala3
 */
public class Pelicula {

    private int film_id;
    private String title;
    private String description;
    private int release_year;
    private int language_id;
    private int original_language;
    private int rental_duration;
    private double rental_rate;
    private int length;
    private double replacement_cost;
    private String rating;
    private String special_features;
    private Timestamp last_update;

    public Pelicula(String title, String description, int release_year, int language_id, int original_language, int rental_duration, double rental_rate, int length, double replacement_cost, String rating, String special_features, Timestamp last_update) {
        //this.film_id = film_id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.language_id = language_id;
        this.original_language = original_language;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.length = length;
        this.replacement_cost = replacement_cost;
        this.rating = rating;
        this.special_features = special_features;
        this.last_update = last_update;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public int getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(int original_language) {
        this.original_language = original_language;
    }

    public int getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(int rental_duration) {
        this.rental_duration = rental_duration;
    }

    public double getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(double rental_rate) {
        this.rental_rate = rental_rate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(double replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public boolean insertarPelicula(String sql, Pelicula objP) {

        boolean f = false;
        ConnectBD con = new ConnectBD();
        CallableStatement stat = null;

        if (con.crearConexion()) {
            try {

                stat = con.getConexion().prepareCall(sql);

                stat.setString(1, objP.getTitle());
                stat.setString(2, objP.getDescription());
                if (objP.getRelease_year() == 0) {
                    stat.setString(3, null);
                } else {
                    stat.setInt(3, objP.getRelease_year());
                }
                stat.setInt(4, objP.getLanguage_id());
                if (objP.getOriginal_language() == 0) {
                    stat.setString(5, null);
                } else {
                    stat.setInt(5, objP.getOriginal_language());
                }
                stat.setInt(6, objP.getRental_duration());
                stat.setDouble(7, objP.getRental_rate());
                if (objP.getLength() == 0) {
                    stat.setString(8, null);
                } else {
                    stat.setInt(8, objP.getLength());
                }
                stat.setDouble(9, objP.getReplacement_cost());
                stat.setString(10, objP.getRating());
                stat.setString(11, objP.getSpecial_features());
                stat.setTimestamp(12, objP.getLast_update());

                stat.execute();
                f = true;

            } catch (SQLException e) {
                System.out.println("Error Pelicula " + e);
                return f;
            }

        }
        return f;
    }

    public boolean modPelicula(String sql) {

        boolean f = false;

        //se establece la coneccion
        ConnectBD objCon = new ConnectBD();

        //Si hay coneccion se crea la sentencia y se ejecuta
        if (objCon.crearConexion()) {
            try {
                Statement sentencia = objCon.getConexion().createStatement();
                sentencia.executeUpdate(sql);
                f = true;
            } catch (SQLException ex) {
                System.out.println("ERROR EN PELICULA  "+ex.toString());
                //ex.printStackTrace();
                f = false;
            }
        }
        return f;

    }

}
