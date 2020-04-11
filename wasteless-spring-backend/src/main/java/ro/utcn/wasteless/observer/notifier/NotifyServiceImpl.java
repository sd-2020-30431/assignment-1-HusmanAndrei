package ro.utcn.wasteless.observer.notifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.wasteless.domain.GroceryItem;
import ro.utcn.wasteless.domain.User;
import ro.utcn.wasteless.repository.GroceryRepository;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private GroceryRepository groceryRepository;

    private final int NO_DAYS = 6;
    private final int CALORIES_DAY = 200;

    @Override
    public Map<User, Map<Date, Integer>> getNonIdealUsersByBurndownRate(Date currentDate) {

        Map<User, List<GroceryItem>> userListMap = mapUsersToExpiredItems(expiresInDays(currentDate));
        Map<User, List<Integer>> userBurndownRate = new HashMap<>();
        for (User user : userListMap.keySet()){
            userBurndownRate.put(user, new ArrayList<>());
            List<Integer> calories = caloriesPerDay(currentDate, userListMap.get(user));
            userBurndownRate.put(user, calories);
        }
        return filterUsersBurndownRate(currentDate, userBurndownRate);
    }

    private Map<User, Map<Date, Integer>> filterUsersBurndownRate(Date startDate, Map<User, List<Integer>> burndown){

        Map<User, Map<Date, Integer>> stat = new HashMap<>();
        for(User user:  burndown.keySet()){
            List<Integer> caloriesPerDay = burndown.get(user);
            Map<Date, Integer> mappedResult = mapResults(startDate, caloriesPerDay);
            stat.put(user, mappedResult);
//            for(int calorie: caloriesPerDay){
//                if(calorie > CALORIES_DAY ){
//                    stat.remove(user);
//                    break;
//                }
//            }
        }
        return stat;
    }
    private Map<Date, Integer> mapResults(Date startDate, List<Integer> burndown){
        Map<Date, Integer> dateEntry = new HashMap<>();
        for (int i = 0 ; i < burndown.size(); i++){
            int calories = burndown.get(i);
            Date date = addDays(startDate, i);
            dateEntry.put(date, calories);
        }
        return dateEntry;
    }

    private List<GroceryItem> expiresInDays(Date currentDate){
        Date daysAfter = addDays(currentDate, NO_DAYS);
        List<GroceryItem> futureExpired = new ArrayList<>();
        List<GroceryItem> all = groceryRepository.findAll();
        all.stream().forEach( groceryItem -> {
              Date consumptionDate = groceryItem.getConsumptionDate();
              Date expirationDate = groceryItem.getExpirationDate();
              if(consumptionDate != null && consumptionDate.after(currentDate) && expirationDate.before(daysAfter)){
                  futureExpired.add(groceryItem);
              }
        }
        );
        return futureExpired;
    }

    private Map<User, List<GroceryItem>> mapUsersToExpiredItems(List<GroceryItem> futureExpired){
        Map<User, List<GroceryItem>> userListMap = new HashMap<>();
        futureExpired.forEach( groceryItem -> {
            User user = groceryItem.getUser();
            if (userListMap.containsKey(user)){
                userListMap.get(user).add(groceryItem);
            }
            else{
                List<GroceryItem> itemList = new ArrayList<>();
                itemList.add(groceryItem);
                userListMap.put(user, itemList);
            }
        });
        return userListMap;
    }


    private List<Integer> fill(int n, int value){
        List<Integer> shame = new ArrayList<>();
        for(int javaDoesntHaveAProperWayToInitArray = 0; javaDoesntHaveAProperWayToInitArray < n; javaDoesntHaveAProperWayToInitArray++){
            shame.add(value);
        }
        return shame;
    }

    private Date maxDate(Date date1, Date date2){
        if (date2.before(date1)){
            return date1;
        }
        return date2;
    }

    private List<Integer> caloriesPerDay(Date currentDate, List<GroceryItem> futureExpired){
        List<Integer> caloriesBurndown = fill(5, 0);
        for(GroceryItem groceryItem : futureExpired ){
            int noConsumptionDays = (int)getDifferenceDays(maxDate(currentDate, groceryItem.getPurchaseDate()), groceryItem.getConsumptionDate()) + 1;

            int averageConsumption = 0;

            averageConsumption = (groceryItem.getCalories() * groceryItem.getQuantity() )/ noConsumptionDays;
            int startDate = getStartDate(currentDate, groceryItem);
            for(int i = startDate; i < noConsumptionDays + startDate; i++){
                int newConsumption = caloriesBurndown.get(i) + averageConsumption;
                caloriesBurndown.remove(i);
                caloriesBurndown.add(i, newConsumption);
            }
        }
        return caloriesBurndown;
    }


    public Date addDays(Date d, int days)
    {
        Date newD = (Date) d.clone();
        newD.setTime( d.getTime() + (long)days*1000*60*60*24 );
        return newD;
    }
    public long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    public int getStartDate(Date currentDate, GroceryItem item){
        Date consumptionDate = item.getConsumptionDate();
        Date purchaseDate = item.getPurchaseDate();
        if (purchaseDate.before(currentDate)){
            return 0;
        }
        return (int)getDifferenceDays(currentDate, purchaseDate);
    }
}
