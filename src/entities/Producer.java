package entities;

import java.util.LinkedList;

public class Producer extends Entity{
    long id;
    EnergyType energyType;
    long pricePerKWh;
    long energyPerDistributor;
    long maxDistributors;
    LinkedList<Distributor> actualDistributors;

    public Producer(long id, EnergyType energyType, long pricePerKWh, long energyPerDistributor, long maxDistributors) {
        this.id = id;
        this.energyType = energyType;
        this.pricePerKWh = pricePerKWh;
        this.energyPerDistributor = energyPerDistributor;
        this.maxDistributors = maxDistributors;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public boolean getRenewableEnergy(){
        return energyType.isRenewable();
    }

    public long getPricePerKWh() {
        return pricePerKWh;
    }

    public void setPricePerKWh(long pricePerKWh) {
        this.pricePerKWh = pricePerKWh;
    }

    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public long getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(long maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public LinkedList<Distributor> getActualDistributors() {
        return actualDistributors;
    }

    public void setActualDistributors(LinkedList<Distributor> actualDistributors) {
        this.actualDistributors = actualDistributors;
    }
}
