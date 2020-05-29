package ro.utcn.wasteless.observer.notifier;

import javafx.beans.InvalidationListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import ro.utcn.wasteless.observer.DateChangeObservable;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
@Component
public class Notifyer implements Observer {

    @Override
    public void update(Observable observable, Object o) {
       DateChangeObservable dateChangeObservable = (DateChangeObservable) observable;
       Date newDate = dateChangeObservable.getNewDate();
       try {
           send();
       }
       catch (Exception ex){
           System.out.println("cannot send messaje");
       }
    }
    @MessageMapping("/wasteless")
    @SendTo("/topic/messages")
    public String send() throws Exception {
        return "Taje";
    }
}
