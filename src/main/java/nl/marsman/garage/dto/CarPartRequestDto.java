package nl.marsman.garage.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CarPartRequestDto {

    //attributen

    @NotBlank
    @Size(min=1, max=100)
    private String description;

    @NotBlank
    @Min(0)
    private double price;

    @NotBlank
    @Min(0)
    private int amountInStock;

    // getters and setters


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
}
