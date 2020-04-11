package ro.utcn.wasteless.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "groceryItems")
public class User extends BaseEntity<Long> {

    @Column
    String username;
    @Column
    String password;

    @OneToMany(mappedBy = "user")
    List<GroceryItem> groceryItems;


}
