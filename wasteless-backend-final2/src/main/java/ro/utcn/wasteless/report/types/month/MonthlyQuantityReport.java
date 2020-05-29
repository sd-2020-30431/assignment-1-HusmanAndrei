package ro.utcn.wasteless.report.types.month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.utcn.wasteless.domain.GroceryItem;
import ro.utcn.wasteless.domain.User;
import ro.utcn.wasteless.repository.GroceryRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MonthlyQuantityReport implements MonthlyReport {

    @Autowired
    private GroceryRepository groceryRepository;

    @Override
    public int getTotalByMonth(Date date, User user) {

        int month = getMonth(date);
        int totalQ = 0;
        List<GroceryItem> willExpire = expiresInMonth(month).stream()
                .filter(item -> item.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
        totalQ = willExpire.stream().mapToInt(GroceryItem::getQuantity).sum();
        return totalQ;

    }

    private List<GroceryItem> expiresInMonth(int month){
        List<GroceryItem> itemList = groceryRepository.findAll().stream()
                .filter(groceryItem -> groceryItem.getConsumptionDate() == null &&
                        getMonth(groceryItem.getExpirationDate()) == month).collect(Collectors.toList());
        return itemList;
    }

    private int getMonth(Date date){
        Calendar calendar = new GregorianCalendar();

        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return month;
    }
}
