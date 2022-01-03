package nl.marsman.garage.dto;

public class CarRequestDto {
    private String brand;
    private String model;
    private int modelYear;
    private String licensePlate;
    //autopapieren nog toevoegen

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }


}
