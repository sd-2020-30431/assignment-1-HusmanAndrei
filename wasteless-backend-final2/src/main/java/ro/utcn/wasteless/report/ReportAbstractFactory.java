package ro.utcn.wasteless.report;

import ro.utcn.wasteless.report.types.month.MonthlyReport;
import ro.utcn.wasteless.report.types.week.WeeklyReport;

public interface ReportAbstractFactory {
    WeeklyReport getWeeklyReport();
    MonthlyReport getMonthlyReport();
}
