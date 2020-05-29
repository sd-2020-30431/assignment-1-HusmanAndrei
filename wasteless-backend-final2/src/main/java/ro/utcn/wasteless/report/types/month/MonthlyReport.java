package ro.utcn.wasteless.report.types.month;

import ro.utcn.wasteless.domain.User;

import java.util.Date;

public interface MonthlyReport {
    int getTotalByMonth(Date date, User user);
}
