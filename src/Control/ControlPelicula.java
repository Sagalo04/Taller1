/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import modelo.Pelicula;

/**
 *
 * @author admin-sala3
 */
public class ControlPelicula {
    
    public boolean modificarPelicula(Pelicula objp) {
       boolean t=false;
        String sql = "update film set title = ?, description = ?, release_year = ?, "+
                "language_id = ?, original_language_id = ?, rental_duration = ?, "+
                "rental_rate = ?, lenght = ?, repacement_cost = ?, rating = ?, "+
                "special_features = ?, last_update = ? where film_id ="+objp.getFilm_id();
        
        t=Pelicula.modpelicula(sql,objp);
        
        return t;
    }    

    boolean insertarCuenta(Pelicula objP) {
        String sql = "[ call insertar_Pelicula(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        boolean f = false;
        
        f = objP.insertarPelicula(sql,objP);
        
        return f;
        
    }
}
