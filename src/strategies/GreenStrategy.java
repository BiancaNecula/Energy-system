package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GreenStrategy implements EnergyStrategy{
    @Override
    public void findProducer(Distributor distributor, List<Producer> producers, int month){
        List<Producer> sortedProducers = producers.stream().sorted(Comparator
                .comparing(Producer::getRenewableEnergy)
                .reversed()
                .thenComparing(Producer::getPricePerKWh)
                .thenComparing(Producer::getEnergyPerDistributor, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        long energy = 0;
        for (Producer p: sortedProducers) {
            if (p.getActualDistributors() == null) {
                p.setActualDistributors(new LinkedList<>());
            }
            if (distributor.getActualProducers() == null) {
                distributor.setActualProducers(new LinkedList<>());
            }
            if (p.getActualDistributors().size() < p.getMaxDistributors()) {
                distributor.getActualProducers().add(p);
                p.getActualDistributors().add(distributor);
                energy += p.getEnergyPerDistributor();
                if(energy >= distributor.getEnergyNeededKW()) {
                    break;
                }
            }
        }
        Double cost = 0.0;
        for(Producer p: distributor.getActualProducers()) {
            cost += p.getEnergyPerDistributor() * p.getPricePerKWh();
        }
        distributor.setProductionCost(Math.round(Math.floor(cost / 10)));
    }
}
