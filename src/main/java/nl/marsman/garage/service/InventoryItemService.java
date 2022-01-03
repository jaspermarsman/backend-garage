package nl.marsman.garage.service;

import nl.marsman.garage.dto.InventoryItemRequestDto;
import nl.marsman.garage.exception.RecordNotFoundException;
import nl.marsman.garage.model.InventoryItem;
import nl.marsman.garage.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryItemService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public Iterable<InventoryItem> getAllInventoryItems(String description) {
        if (description.isEmpty()) {
            return inventoryItemRepository.findAll();
        }
        else {
            return inventoryItemRepository.findAllByDescriptionContainingIgnoreCase(description);
        }
    }

    public InventoryItem getInventoryItem(int id) {
        Optional<InventoryItem> optionalInventoryItem = inventoryItemRepository.findById(id);

        if (optionalInventoryItem.isPresent()) {
            return optionalInventoryItem.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void deleteInventoryItem(int id) {
        if (inventoryItemRepository.existsById(id)) {
            inventoryItemRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public int addInventoryItem(InventoryItemRequestDto inventoryItemRequestDto) {

        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setDescription(inventoryItemRequestDto.getDescription());
        inventoryItem.setPrice(inventoryItemRequestDto.getPrice());
        inventoryItem.setAmountInStock(inventoryItemRequestDto.getAmountInStock());

        InventoryItem newInventoryItem = inventoryItemRepository.save(inventoryItem);
        return newInventoryItem.getId();
    }

    public void updateInventoryItem(int id, InventoryItem inventoryItem) {
        Optional<InventoryItem> optionalInventoryItem = inventoryItemRepository.findById(id);

        if (optionalInventoryItem.isPresent()) {
            InventoryItem storedInventoryItem = optionalInventoryItem.get();

            inventoryItem.setId(storedInventoryItem.getId());
            inventoryItemRepository.save(inventoryItem);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }




}
