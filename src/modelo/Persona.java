/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Persona {

    private String firstName = null;
    private String lastName = null;
    private String pelicula = null;

    public Persona() {
    }

    public Persona(String firstName, String lastName,String Pelicula) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pelicula = Pelicula;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    
}
