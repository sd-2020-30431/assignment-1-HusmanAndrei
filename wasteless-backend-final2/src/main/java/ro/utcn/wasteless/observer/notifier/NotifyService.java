package ro.utcn.wasteless.observer.notifier;


import ro.utcn.wasteless.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface NotifyService {
    Map<User, Map<Date, Integer>> getNonIdealUsersByBurndownRate(Date currentDate);
}
