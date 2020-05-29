package ro.utcn.wasteless.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.utcn.wasteless.report.types.month.MonthlyCaloricReport;
import ro.utcn.wasteless.report.types.month.MonthlyQuantityReport;
import ro.utcn.wasteless.report.types.month.MonthlyReport;
import ro.utcn.wasteless.report.types.week.WeeklyQuantityReport;
import ro.utcn.wasteless.report.types.week.WeeklyReport;

@Component
public class ReportQuantityFactory implements ReportAbstractFactory {
    @Autowired
    MonthlyQuantityReport monthlyQuantityReport;

    @Autowired
    WeeklyQuantityReport weeklyQuantityReport;

    @Override
    public WeeklyReport getWeeklyReport() {
        return weeklyQuantityReport;
    }

    @Override
    public MonthlyReport getMonthlyReport() {
        return monthlyQuantityReport;
    }
}
