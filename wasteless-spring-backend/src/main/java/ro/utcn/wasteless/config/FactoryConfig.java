package ro.utcn.wasteless.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ro.utcn.wasteless.report.ReportAbstractFactory;
import ro.utcn.wasteless.report.ReportCaloricFactory;
import ro.utcn.wasteless.report.ReportQuantityFactory;

@Configuration
public class FactoryConfig {
    @Autowired
    ReportQuantityFactory reportQuantityFactory;

    @Autowired
    ReportCaloricFactory reportCaloricFactory;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public ReportAbstractFactory createFactory(boolean isCaloric) {
        if (isCaloric){
            return reportCaloricFactory;
        }
        return reportQuantityFactory;
    }
}
