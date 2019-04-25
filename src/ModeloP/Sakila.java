/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloP;

import Control.ConnectBD;
/**
 *
 * @author Dani D
 */
public class Sakila {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ConnectBD objC = new ConnectBD();
        boolean t =  objC.crearConexion();
        if(t){
            System.out.print("conexion exitosa");
        }else{
            System.out.print("conexion NO exitosa");
        }
    }
    
}
