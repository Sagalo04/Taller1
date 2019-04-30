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
    private int rentalid;

    public Persona() {
    }

    public Persona(String firstName, String lastName, String Pelicula) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pelicula = Pelicula;
    }

    public Persona(String firstName, String lastName, int rentalid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rentalid = rentalid;
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

    public int getRentalid() {
        return rentalid;
    }

    public void setRentalid(int rentalid) {
        this.rentalid = rentalid;
    }

}
