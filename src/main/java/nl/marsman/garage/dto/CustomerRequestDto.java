package nl.marsman.garage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CustomerRequestDto {
    //attributen

    @NotBlank
    @Size(min=1, max=25)
    private String firstName;

    @NotBlank
    @Size(min=1, max=51)
    private String secondName;

    //getters setters


    public String getFirstName() {
        return firstName;
    }


    public String getSecondName() {
        return secondName;
    }

}
