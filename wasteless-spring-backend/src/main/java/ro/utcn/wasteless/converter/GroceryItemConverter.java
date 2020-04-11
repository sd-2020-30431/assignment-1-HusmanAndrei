package ro.utcn.wasteless.converter;

import org.springframework.stereotype.Component;
import ro.utcn.wasteless.domain.GroceryItem;
import ro.utcn.wasteless.dto.GroceryItemDto;

import java.util.Date;

@Component
public class GroceryItemConverter extends BaseConverter<GroceryItem, GroceryItemDto> {
    @Override
    public GroceryItem convertToModel(GroceryItemDto groceryItemDto) {
        Date purchaseDate = new Date(groceryItemDto.getPurchaseDate());
        Date expirationDate = new Date(groceryItemDto.getExpirationDate());
        Date consumptionDate = new Date(groceryItemDto.getConsumptionDate());
        GroceryItem item =  GroceryItem.builder().calories(groceryItemDto.getCalories()).name(groceryItemDto.getName()).quantity(groceryItemDto.getQuantity())
                .purchaseDate(purchaseDate).expirationDate(expirationDate).consumptionDate(consumptionDate).build();
        item.setId(groceryItemDto.getID());
        return item;
    }

    @Override
    public GroceryItemDto convertToDto(GroceryItem entity) {
        Long purchaseDate = entity.getPurchaseDate().getTime();
        Long expirationDate = entity.getExpirationDate().getTime();
        Long consumptionDate = entity.getConsumptionDate().getTime();
        return GroceryItemDto.builder().name(entity.getName()).calories(entity.getCalories()).ID(entity.getId()).quantity(entity.getQuantity())
                .consumptionDate(consumptionDate).expirationDate(expirationDate).purchaseDate(purchaseDate).build();
    }
}
