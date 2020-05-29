package ro.utcn.wasteless.controller;


import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.utcn.wasteless.converter.GroceryItemConverter;
import ro.utcn.wasteless.converter.UserConverter;
import ro.utcn.wasteless.domain.GroceryItem;
import ro.utcn.wasteless.domain.User;
import ro.utcn.wasteless.dto.GroceryItemDto;
import ro.utcn.wasteless.dto.UserDto;
import ro.utcn.wasteless.dto.reports.ReportDto;
import ro.utcn.wasteless.observer.notifier.NotifyService;
import ro.utcn.wasteless.report.FactoryProvider;
import ro.utcn.wasteless.report.ReportAbstractFactory;
import ro.utcn.wasteless.report.service.ReportService;
import ro.utcn.wasteless.report.types.month.MonthlyReport;
import ro.utcn.wasteless.report.types.week.WeeklyReport;
import ro.utcn.wasteless.service.WastelessService;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
public class WastelessController {

    @Autowired
    WastelessService service;

    @Autowired
    UserConverter userConverter;

    @Autowired
    GroceryItemConverter groceryItemConverter;

    @Autowired
    NotifyService notifyService;

    @Autowired
    ReportService reportService;

    private Date currentDate = new Date();

    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto userDto){
        User user = userConverter.convertToModel(userDto);
        user = service.saveUser(user);
        return userConverter.convertToDto(user);
    }

    @PostMapping("/login")
    public UserDto loginUser(@RequestBody UserDto userInfo){
        Optional<User> user = service.getUser(userInfo.getName(), userInfo.getPassword());
        if(user.isEmpty()){
            return userInfo;
        }
        return userConverter.convertToDto(user.get());
    }


    private Long getUserId(String stringId){
        String actualId = stringId.split(" ")[1];
        Long userID = Long.parseLong(actualId);
        return userID;
    }

    private Optional<User> getUser(String stringId){
        Long id = getUserId(stringId);
        return service.getUserById(id);
    }

    @PostMapping("/grocery")
    public GroceryItemDto addGrocery(@RequestBody GroceryItemDto itemDto, @RequestHeader(name="Authorization") String stringId){
        Optional<User> user = getUser(stringId);
        GroceryItem item = groceryItemConverter.convertToModel(itemDto);
        if(user.isEmpty()){
            return null;
        }
        return groceryItemConverter.convertToDto(service.addGrocery(item, user.get()));
    }



    @Autowired
    private FactoryProvider factoryProvider;

    @GetMapping("/stats/weekly")
    public int getWeekly(@RequestParam boolean toCaloric, @RequestHeader(name="Authorization") String stringId){
        ReportAbstractFactory abstractFactory = factoryProvider.getFactory(toCaloric);
        WeeklyReport weeklyReport = abstractFactory.getWeeklyReport();
        Optional<User> userOptional = getUser(stringId);
        if (userOptional.isEmpty()){
            return -1;
        }
        return weeklyReport.getTotalByWeek(currentDate, userOptional.get());
    }
    @GetMapping("/stats/monthly")
    public int getMonthly(@RequestParam boolean toCaloric,  @RequestHeader(name="Authorization") String stringId){
        ReportAbstractFactory abstractFactory = factoryProvider.getFactory(toCaloric);
        MonthlyReport monthlyReport = abstractFactory.getMonthlyReport();
        Optional<User> userOptional = getUser(stringId);
        if (userOptional.isEmpty()){
            return -1;
        }
        return monthlyReport.getTotalByMonth(currentDate, userOptional.get());
    }

    @GetMapping("/stats/all")
    public ReportDto getAllReports(@RequestHeader(name="Authorization") String stringId){
        Optional<User> user = getUser(stringId);
        if(user.isEmpty()){
            return null;
        }
        return reportService.getAllReports(user.get(), currentDate);

    }


    @GetMapping("/grocery")
    public List<GroceryItemDto> getItemsByUser(@RequestHeader(name="Authorization") String stringId){
        Optional<User> user = getUser(stringId);
        if(user.isEmpty()){
            return null;
        }
        List<GroceryItemDto> items = groceryItemConverter.convertAllToDtos(service.getItemsByUser(user.get()));
        return items;
    }

    @PutMapping("/date")
    public Date changeDate(@RequestBody Long newDateFormat){
        try{
            this.currentDate = new Date(newDateFormat);
            return this.currentDate;
        }
        catch (Exception ex){
            return null;
        }
    }

    @GetMapping("/report")
    public Object getInvalidUsers(){
        Map<User, Map<Date, Integer>> stat = notifyService.getNonIdealUsersByBurndownRate(currentDate);
        Map<UserDto, Map<Date, Integer>> dtoStat = new HashMap<>();
        stat.keySet().forEach(key -> {
            UserDto dto = userConverter.convertToDto(key);
            dto.setID(null);
            dtoStat.put(dto, stat.get(key));
        });
        return dtoStat;
    }

    @DeleteMapping("/donate")
    public GroceryItemDto donateItem(@RequestParam Long itemId, @RequestHeader(name="Authorization")String stringId){
        Optional<GroceryItem> item = service.deleteItem(itemId);
        if(item.isEmpty()){
            return null;
        }
        return groceryItemConverter.convertToDto(item.get());
    }






}
