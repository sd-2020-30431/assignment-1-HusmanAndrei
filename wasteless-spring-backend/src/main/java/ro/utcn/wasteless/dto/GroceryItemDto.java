package ro.utcn.wasteless.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import ro.utcn.wasteless.domain.GroceryItem;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemDto implements DTO<GroceryItem, Long> {
    Long ID;

    String name;

    int quantity;
    int calories;

    Long purchaseDate;
    Long expirationDate;
    Long consumptionDate;

}
