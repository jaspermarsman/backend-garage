package nl.marsman.garage.repository;

import nl.marsman.garage.model.InventoryItem;
import org.springframework.data.repository.CrudRepository;

public interface InventoryItemRepository extends CrudRepository<InventoryItem, Integer> {
    Iterable<InventoryItem> findAllByDescriptionContainingIgnoreCase(String description);
}
