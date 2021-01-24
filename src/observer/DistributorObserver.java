package observer;

import entities.Distributor;
import entities.Producer;
import strategies.EnergyContext;
import strategies.EnergyStrategy;
import strategies.StrategyFactory;

import java.util.List;

public final class DistributorObserver implements Observer {

    private Distributor distributor;

    public DistributorObserver(Distributor distributor) {
        this.distributor = distributor;
    }

    @Override
    public void update(List<Producer> producers, int month) {
        for (Producer p : producers) {
            if (p.getActualDistributors() != null
                    && p.getActualDistributors().contains(distributor)) {
                p.getActualDistributors().remove(distributor);
                distributor.getActualProducers().remove(p);
            }
        }
        StrategyFactory strategyFactory = new StrategyFactory();
        EnergyStrategy energyStrategy = strategyFactory.createStrategy(distributor);
        EnergyContext context = new EnergyContext(energyStrategy);
        context.executeStrategy(distributor, producers, month);
    }
}
