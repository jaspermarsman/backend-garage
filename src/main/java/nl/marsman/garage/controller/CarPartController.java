package nl.marsman.garage.controller;

import nl.marsman.garage.dto.CarPartRequestDto;
import nl.marsman.garage.model.CarPart;
import nl.marsman.garage.service.CarPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CarPartController {

    @Autowired
    private CarPartService carPartService;

    @GetMapping(value = "/inventoryitems")
    public ResponseEntity<Object> getAllInventoryItems(@RequestParam(name="description", defaultValue = "") String description) {
        return ResponseEntity.ok(carPartService.getAllInventoryItems(description));
    }

    @GetMapping(value = "/inventoryitems/{id}")
    public ResponseEntity<Object> getInventoryItem(@PathVariable int id) {
        return ResponseEntity.ok(carPartService.getInventoryItem(id));
    }

    @DeleteMapping(value = "/inventoryitems/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        carPartService.deleteInventoryItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/inventoryitems")
    public ResponseEntity<Object> addInventoryItem(@Valid @RequestBody CarPartRequestDto carPartRequestDto) {
        int newId = carPartService.addInventoryItem(carPartRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/inventoryitems/{id}")
    public ResponseEntity<Object> updateinventoryItem(@PathVariable int id, @RequestBody CarPart carPart) {
        carPartService.updateInventoryItem(id, carPart);

        return ResponseEntity.noContent().build();
    }

//    @PatchMapping(value = "/inventoryitems/{id}")
//    public ResponseEntity<Object> partialUpdateInventoryItem(@PathVariable int id, @RequestBody InventoryItem inventoryItem) {
//        inventoryItemService.partialUpdateInventoryItem(id, inventoryItem);
//
//        return ResponseEntity.noContent().build();
//    }



}
