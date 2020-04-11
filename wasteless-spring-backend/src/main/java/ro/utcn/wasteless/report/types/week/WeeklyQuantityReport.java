package ro.utcn.wasteless.report.types.week;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.utcn.wasteless.domain.User;
import ro.utcn.wasteless.repository.GroceryRepository;

import java.util.Date;

@Component
public class WeeklyQuantityReport implements WeeklyReport{

    @Autowired
    private GroceryRepository groceryRepository;

    @Override
    public int getTotalByWeek(Date date, User user) {
        return 0;
    }
}
