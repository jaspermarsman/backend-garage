package nl.marsman.garage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "reparations")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reparation extends Appointment {
    //attributen


    @JsonIgnoreProperties("reparations")
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car scheduledFor;



    //getters and setters


    public Car getScheduledFor() {
        return scheduledFor;
    }

    public void setScheduledFor(Car scheduledFor) {
        this.scheduledFor = scheduledFor;
    }
}
