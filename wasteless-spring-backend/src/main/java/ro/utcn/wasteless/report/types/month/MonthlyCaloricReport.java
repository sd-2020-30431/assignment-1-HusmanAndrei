package ro.utcn.wasteless.report.types.month;

import org.springframework.stereotype.Component;
import ro.utcn.wasteless.domain.User;

import java.util.Date;

@Component
public class MonthlyCaloricReport implements MonthlyReport {

    @Override
    public int getTotalByMonth(Date date, User user) {
        return 1;
    }
}
