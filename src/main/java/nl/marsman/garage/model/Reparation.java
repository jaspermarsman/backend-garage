package nl.marsman.garage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reparations")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reparation extends Appointment {
    //attributen


    @JsonIgnoreProperties("reparations")
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car scheduledFor;

    @ManyToMany(mappedBy = "usedForReparation")
    private List<CarPart> reparationParts = new ArrayList<>();


//    @ManyToMany(mappedBy = "reparations", fetch = FetchType.EAGER)
//    private List<CarPart> carParts = new ArrayList<>();

//    @OneToMany(mappedBy = "reparation_id", cascade = CascadeType.ALL, orphanRemoval = true)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<CarPart> carParts = new ArrayList<>();

    //getters and setters

    public Car getScheduledFor() {
        return scheduledFor;
    }

    public void setScheduledFor(Car scheduledFor) {
        this.scheduledFor = scheduledFor;
    }



//    public List<CarPart> getCarParts() {
//       return carParts;
//    }
//
//    public void setCarParts(List<CarPart> carParts) {
//        this.carParts = carParts;
//    }


//    public List<CarPart> getCarParts() {
//        return carParts;
//    }
//
//    public void setCarParts(List<CarPart> carParts) {
//        this.carParts = carParts;
//    }


    public List<CarPart> getReparationParts() {
        return reparationParts;
    }

    public void setReparationParts(List<CarPart> reparationParts) {
        this.reparationParts = reparationParts;
    }
}
