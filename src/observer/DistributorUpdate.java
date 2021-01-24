package observer;

import entities.Producer;

import java.util.ArrayList;
import java.util.List;

public final class DistributorUpdate implements Subject {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    public void notifyUpdate(List<Producer> producers, int month) {
        for (Observer o: observers) {
            o.update(producers, month);
        }
    }
}
