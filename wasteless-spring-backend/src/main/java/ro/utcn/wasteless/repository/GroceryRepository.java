package ro.utcn.wasteless.repository;

import org.springframework.stereotype.Repository;
import ro.utcn.wasteless.domain.GroceryItem;

@Repository
public interface GroceryRepository extends BaseRepository<GroceryItem, Long> {

}
