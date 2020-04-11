package ro.utcn.wasteless.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryProvider {
    @Autowired
    ReportCaloricFactory reportCaloricFactory;

    @Autowired
    ReportQuantityFactory reportQuantityFactory;

    public ReportAbstractFactory getFactory(boolean isCaloric){
        if(isCaloric){
            return reportCaloricFactory;
        }
        return reportQuantityFactory;
    }
}
