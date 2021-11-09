package nl.marsman.garage.model;

import javax.persistence.*;

@Entity
@Table(name = "Klanten")
public class Customer {

    //attributen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generate id (++)
    private int id;

    private String firstName;
    private String secondName;

    //constructor

    //getters en setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
