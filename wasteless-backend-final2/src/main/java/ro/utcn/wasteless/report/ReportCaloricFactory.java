package ro.utcn.wasteless.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.utcn.wasteless.report.types.month.MonthlyCaloricReport;
import ro.utcn.wasteless.report.types.month.MonthlyReport;
import ro.utcn.wasteless.report.types.week.WeeklyCaloricReport;
import ro.utcn.wasteless.report.types.week.WeeklyReport;

@Component
public class ReportCaloricFactory implements ReportAbstractFactory {

    @Autowired
    WeeklyCaloricReport weeklyCaloricReport;

    @Autowired
    MonthlyCaloricReport monthlyCaloricReport;

    @Override
    public WeeklyReport getWeeklyReport() {
        return weeklyCaloricReport;
    }

    @Override
    public MonthlyReport getMonthlyReport() {
        return monthlyCaloricReport;
    }
}
