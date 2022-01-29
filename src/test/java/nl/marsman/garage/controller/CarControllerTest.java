package nl.marsman.garage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.marsman.garage.GarageApplication;
import nl.marsman.garage.dto.CarRequestDto;
import nl.marsman.garage.model.Car;
import nl.marsman.garage.security.JwtUtil;
import nl.marsman.garage.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CarController.class)
@ContextConfiguration(classes={GarageApplication.class})
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CarService carService;

//    @Test
//    public void testEndpointCars() throws Exception {
//
//        Car car = new Car("Fiat", "Ducato", "63-DKD-9");
//        List<Car> allCars = Arrays.asList(car);
//
//        given(carService.getCars("63-DKD-9")).willReturn(allCars);
//
//        mvc.perform(get("/cars")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].model", is("Ducato")));
//    }

    @Test
    public void testAddCar() throws Exception {

        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setLicensePlate("63-DKD-9");

        Car car = new Car();
        car.setLicensePlate(carRequestDto.getLicensePlate());

        mvc.perform(post("/cars")
                        .with(user("admin").roles("ADMIN"))
                        .content(asJsonString(carRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    }




