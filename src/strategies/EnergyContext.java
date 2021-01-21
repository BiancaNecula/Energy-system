package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;

public class EnergyContext {
    private EnergyStrategy strategy;

    public EnergyContext(EnergyStrategy strategy){
        this.strategy = strategy;
    }

    public void executeStrategy(Distributor d, List<Producer> producers){
        strategy.findProducer(d, producers);
    }
}
