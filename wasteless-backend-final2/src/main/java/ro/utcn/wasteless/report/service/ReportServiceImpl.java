package ro.utcn.wasteless.report.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.wasteless.domain.User;
import ro.utcn.wasteless.dto.reports.ReportDto;
import ro.utcn.wasteless.report.FactoryProvider;
import ro.utcn.wasteless.report.ReportAbstractFactory;

import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    private FactoryProvider factoryProvider;

    @Override
    public ReportDto getAllReports(User user, Date date) {
        List<Integer> caloricReports = getReports(user, date, true);
        List<Integer> quantityReports = getReports(user, date, false);
        ReportDto reportDto = ReportDto.builder().weekCalories(caloricReports.get(0)).monthCalories(caloricReports.get(1))
                .weekQuantity(quantityReports.get(0)).monthQuantity(quantityReports.get(1)).build();
        return reportDto;


    }

    private List<Integer> getReports(User user, Date date, boolean caloric){
        ReportAbstractFactory reportAbstractFactory = factoryProvider.getFactory(caloric);
        int month = reportAbstractFactory.getMonthlyReport().getTotalByMonth(date, user);
        int week = reportAbstractFactory.getWeeklyReport().getTotalByWeek(date, user);
        return List.of(week, month);
    }


}
