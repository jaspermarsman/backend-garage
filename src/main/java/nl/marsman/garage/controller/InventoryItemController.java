package nl.marsman.garage.controller;

import nl.marsman.garage.dto.InventoryItemRequestDto;
import nl.marsman.garage.model.InventoryItem;
import nl.marsman.garage.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class InventoryItemController {

    @Autowired
    private InventoryItemService inventoryItemService;

    @GetMapping(value = "/inventoryitems")
    public ResponseEntity<Object> getAllInventoryItems(@RequestParam(name="description", defaultValue = "") String description) {
        return ResponseEntity.ok(inventoryItemService.getAllInventoryItems(description));
    }

    @GetMapping(value = "/inventoryitems/{id}")
    public ResponseEntity<Object> getInventoryItem(@PathVariable int id) {
        return ResponseEntity.ok(inventoryItemService.getInventoryItem(id));
    }

    @DeleteMapping(value = "/inventoryitems/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        inventoryItemService.deleteInventoryItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/inventoryitems")
    public ResponseEntity<Object> addInventoryItem(@Valid @RequestBody InventoryItemRequestDto inventoryItemRequestDto) {
        int newId = inventoryItemService.addInventoryItem(inventoryItemRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/inventoryitems/{id}")
    public ResponseEntity<Object> updateinventoryItem(@PathVariable int id, @RequestBody InventoryItem inventoryItem) {
        inventoryItemService.updateInventoryItem(id, inventoryItem);

        return ResponseEntity.noContent().build();
    }

//    @PatchMapping(value = "/inventoryitems/{id}")
//    public ResponseEntity<Object> partialUpdateInventoryItem(@PathVariable int id, @RequestBody InventoryItem inventoryItem) {
//        inventoryItemService.partialUpdateInventoryItem(id, inventoryItem);
//
//        return ResponseEntity.noContent().build();
//    }



}
