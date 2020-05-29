package ro.utcn.wasteless.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class GroceryItem extends BaseEntity<Long> {

    String name;
    int quantity;
    int calories;
    @Temporal(TemporalType.TIMESTAMP)
    Date purchaseDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date consumptionDate;
    @ManyToOne
    private User user;

}
