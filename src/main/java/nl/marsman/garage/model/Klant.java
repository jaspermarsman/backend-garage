package nl.marsman.garage.model;

import javax.persistence.Entity;

@Entity
public class Klant {

    //attributen
    private String name;

    //constructor

    //getters en setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
