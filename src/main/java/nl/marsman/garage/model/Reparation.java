package nl.marsman.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "reparations")
public class Reparation {
    //attributen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
