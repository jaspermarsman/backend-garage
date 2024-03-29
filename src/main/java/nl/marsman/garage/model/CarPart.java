package nl.marsman.garage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_parts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CarPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private double price;
    private int amountInStock;

    @JsonIgnoreProperties("usedForReparation")
    @ManyToMany
    @JoinTable(name = "used_car_parts", joinColumns = @JoinColumn(name = "car_parts_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "reparation_id") )
    private List<Reparation> usedForReparation = new ArrayList<>();

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable
//    private List<Reparation> reparations;

//    @ManyToOne
//    @JoinColumn(name = "reparation_id", referencedColumnName = "id")
//    private Reparation carPart;

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

//    public Reparation getCarPart() {
//        return carPart;
//    }
//
//    public void setCarPart(Reparation carPart) {
//        this.carPart = carPart;
//    }


//    public List<Reparation> getReparations() {
//        return reparations;
//    }
//
//    public void setReparations(List<Reparation> reparations) {
//        this.reparations = reparations;
//    }


    public List<Reparation> getUsedForReparation() {
        return usedForReparation;
    }

    public void setUsedForReparation(List<Reparation> usedForReparation) {
        this.usedForReparation = usedForReparation;
    }
}
