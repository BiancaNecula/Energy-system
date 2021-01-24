package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;

public interface EnergyStrategy {
    /**
     * @param distributor
     * @param producers
     * @param month
     */
    void findProducer(Distributor distributor, List<Producer> producers, int month);
}
