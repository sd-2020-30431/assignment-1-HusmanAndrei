package ro.utcn.wasteless.observer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("deprecation")
@Component
@Data
public class DateChangeObservable extends Observable {

    private Date newDate = null;

    public void changeDate(Date newDate){
        this.newDate = newDate;
        notifyAll();
    }



}
