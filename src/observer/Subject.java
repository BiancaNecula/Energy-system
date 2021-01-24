package observer;

import entities.Producer;

import java.util.List;

public interface Subject {
    /**
     * @param o
     */
    void attach(Observer o);

    /**
     * @param producerList
     * @param month
     */
    void notifyUpdate(List<Producer> producerList, int month);
}
