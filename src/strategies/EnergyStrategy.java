package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;

public interface EnergyStrategy {
    void findProducer(Distributor distributor, List<Producer> producers, int month);
}
