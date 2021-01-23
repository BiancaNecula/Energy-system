package entities;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Distributor extends Entity {
    private long id;
    private long contractLength;
    private long initialBudget;
    private long infrastructureCost;
    private long energyNeededKW;
    private String producerStrategy;
    private long productionCost;
    private int numberOfClients;
    private long money;
    private long contractCost;
    private Map<Consumer, Long> contracts = new LinkedHashMap<>();
    private boolean isBankrupt = false;
    private List<Producer> actualProducers;
    public boolean isChanged = false;

    public Distributor(final long id, final long contractLength,
                       final long initialBudget, final long infrastructureCost,
                       final long energyNeededKW, final String producerStrategy) {
        this.id = id;
        this.contractLength = contractLength;
        this.initialBudget = initialBudget;
        this.infrastructureCost = infrastructureCost;
        this.energyNeededKW = energyNeededKW;
        this.producerStrategy = producerStrategy;
    }

    /**
     * @return distributor's id
     */
    public long getId() {
        return id;
    }

    /**
     * @return distributor's budget
     */
    public long getMoney() {
        return money;
    }

    /**
     * @param money distributor's budget
     */
    public void setMoney(final long money) {
        this.money = money;
    }

    /**
     * @return number of rates for distributor's contract
     */
    public long getContractLength() {
        return contractLength;
    }

    /**
     * @return distributor's initial budget
     */
    public long getInitialBudget() {
        return initialBudget;
    }

    /**
     * @return distributor's infrastructure cost
     */
    public long getInfrastructureCost() {
        return infrastructureCost;
    }

    /**
     * @return distributor's production cost
     */
    public long getProductionCost() {
        return productionCost;
    }

    /**
     * @return distributor's profit
     */
    public long getProfit() {
        return Math.round(Math.floor(0.2 * this.productionCost));
    }

    /**
     * @return distributor's number of clients
     */
    public int getNumberOfClients() {
        return numberOfClients;
    }

    /**
     * @param numberOfClients distributor's number of clients
     */
    public void setNumberOfClients(final int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    /**
     * @param contractLength distributor's number of rates for contract
     */
    public void setContractLength(final long contractLength) {
        this.contractLength = contractLength;
    }

    /**
     * @param infrastructureCost distributor's infrastructure cost
     */
    public void setInfrastructureCost(final long infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    /**
     * @param productionCost distributor's production cost
     */
    public void setProductionCost(final long productionCost) {
        this.productionCost = productionCost;
    }

    /**
     * @return distributor's clients
     */
    public Map<Consumer, Long> getContracts() {
        return contracts;
    }

    /**
     * @param contracts distributor's clients
     */
    public void setContracts(final Map<Consumer, Long> contracts) {
        this.contracts = contracts;
    }

    /**
     * @return true if is bankrupt, false otherway
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * @param bankrupt true if is bankrupt, false otherway
     */
    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public List<Producer> getActualProducers() {
        return actualProducers;
    }

    public void setActualProducers(List<Producer> actualProducers) {
        this.actualProducers = actualProducers;
    }

    public long getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(long energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public long getContractCost() {
        return contractCost;
    }

    public void setContractCost(long contractCost) {
        this.contractCost = contractCost;
    }

    /**
     * @return pretty print
     */
    @Override
    public String toString() {
        return "Distributor{"
                + "id=" + id
                + ", contractLength=" + contractLength
                + ", initialBudget=" + initialBudget
                + ", infrastructureCost=" + infrastructureCost
                + ", productionCost=" + productionCost
                + '}';
    }

}
