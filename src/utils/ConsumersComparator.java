package utils;

import entities.Consumer;

import java.util.Comparator;

public class ConsumersComparator implements Comparator<Consumer> {
    /**
     * @param o1 first consumer
     * @param o2 second consumer
     * @return the a negative integer, zero, or a positive integer as the first argument is
     * less than, equal to, or greater than the second
     */
    @Override
    public int compare(final Consumer o1, final Consumer o2) {
        return (int) (o1.getId() - o2.getId());
    }
}
