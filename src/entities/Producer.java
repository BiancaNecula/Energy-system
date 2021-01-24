package entities;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Producer extends Entity {
    private long id;
    private String energyType;
    private Double pricePerKWh;
    private long energyPerDistributor;
    private long maxDistributors;
    private LinkedList<Distributor> actualDistributors;
    private Map<Integer, List<Long>> monthlyDistributors = new HashMap();

    public Producer(long id, String energyType, long maxDistributors,
                    Double pricePerKWh, long energyPerDistributor) {
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
        return EnergyType.valueOf(energyType);
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public boolean getRenewableEnergy() {

        return EnergyType.valueOf(energyType).isRenewable();
    }

    public Double getPricePerKWh() {
        return pricePerKWh;
    }

    public void setPricePerKWh(Double pricePerKWh) {
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

    public Map<Integer, List<Long>> getMonthlyDistributors() {
        return monthlyDistributors;
    }

    public void setMonthlyDistributors(Map<Integer, List<Long>> monthlyDistributors) {
        this.monthlyDistributors = monthlyDistributors;
    }

    public void setActualDistributors(LinkedList<Distributor> actualDistributors) {
        this.actualDistributors = actualDistributors;
    }
}
