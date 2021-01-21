package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuantityStrategy implements EnergyStrategy{
    @Override
    public void findProducer(Distributor distributor, List<Producer> producers) {
        List<Producer> sortedProducers = producers.stream().sorted(Comparator
                .comparing(Producer::getEnergyPerDistributor))
                .collect(Collectors.toList());
        for (Producer p: sortedProducers) {
            if (p.getActualDistributors().size() < p.getMaxDistributors()) {
                distributor.setActualProducer(p);
                p.getActualDistributors().add(distributor);
                break;
            }
        }
    }
}
