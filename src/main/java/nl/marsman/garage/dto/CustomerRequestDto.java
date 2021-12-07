package nl.marsman.garage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CustomerRequestDto {
    //attributen

    @NotBlank
    @Size(min=1, max=25, message = "Firstname should at least contain 1 character and have a maximum of 25 characters")
    private String firstName;

    @NotBlank
    @Size(min=1, max=50, message = "SecondName should at least contain 1 character and have a maximum of 50 characters")
    private String secondName;

    //getters setters


    public String getFirstName() {
        return firstName;
    }


    public String getSecondName() {
        return secondName;
    }

}
