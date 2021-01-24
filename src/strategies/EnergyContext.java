package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;

public final class EnergyContext {
    private EnergyStrategy strategy;

    public EnergyContext(EnergyStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * @param d
     * @param producers
     * @param month
     */
    public void executeStrategy(Distributor d, List<Producer> producers, int month) {
        strategy.findProducer(d, producers, month);
    }
}
