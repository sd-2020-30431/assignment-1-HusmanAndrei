package ro.utcn.wasteless.report.types.week;

import ro.utcn.wasteless.domain.User;

import java.util.Date;

public interface WeeklyReport {
    int getTotalByWeek(Date date, User user);
}
