package ro.utcn.wasteless.report.types.week;

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
public class WeeklyCaloricReport implements WeeklyReport{

    @Autowired
    private GroceryRepository groceryRepository;

    @Override
    public int getTotalByWeek(Date date, User user) {

        int week = getWeek(date);
        int totalCalories = 0;
        List<GroceryItem> willExpire = expiresInWeek(week).stream()
                .filter(item -> item.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
        totalCalories = willExpire.stream().mapToInt(groceryItem -> groceryItem.getCalories() * groceryItem.getQuantity()).sum();
        return totalCalories;

    }

    private List<GroceryItem> expiresInWeek(int week){
        List<GroceryItem> itemList = groceryRepository.findAll().stream()
                .filter(groceryItem -> groceryItem.getConsumptionDate() == null &&
                        getWeek(groceryItem.getExpirationDate()) == week).collect(Collectors.toList());
        return itemList;
    }

    private int getWeek(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        return week;
    }
}