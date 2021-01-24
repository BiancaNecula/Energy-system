package observer;

import entities.Producer;

import java.util.List;

public interface Observer {
    /**
     * @param producers
     * @param month
     */
    void update(List<Producer> producers, int month);
}
