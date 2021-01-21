package input;

import entities.Consumer;
import entities.Distributor;
import entities.Producer;
import strategies.EnergyContext;
import strategies.EnergyStrategy;
import strategies.StrategyFactory;
import updates.UpdateDistributorChanges;
import utils.DistributorsComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;


public class Solver {

    /**
     * the main function for program logic
     * @param input input with all data
     */
    public void solve(final InputGame input) {
        firstMonth(input.getConsumers(), input.getDistributors(), input.getProducers());
        for (int i = 0; i < input.getNumberOfTurns(); i++) {
            modifyDistributors(input.getDistributors(), input.getDistributorChanges().get(i));
            if (input.getNewConsumers().get(i) != null) {
                initializeNewConsumers(input.getNewConsumers().get(i), input.getDistributors());
            }
            input.getConsumers().addAll(input.getNewConsumers().get(i));
            ArrayList<Long> indices = new ArrayList<>();
            for (Consumer c : input.getNewConsumers().get(i)) {
                indices.add(c.getId());
            }
            checkContracts(input.getConsumers(), input.getDistributors(), indices);
            taxDistributors(input.getDistributors());
            input.getDistributors().sort(Comparator.comparing(Distributor::getId));
        }
    }

    /**
     * First month for new consumers
     * @param newConsumers list of new consumers
     * @param distributors list of distributors
     */
    private void initializeNewConsumers(final List<Consumer> newConsumers,
                                        final List<Distributor> distributors) {
        Collections.sort(distributors, new DistributorsComparator());
        Distributor minDistributor = Collections.min(distributors,
                new DistributorsComparator());
        if (minDistributor.isBankrupt()) {
            for (Distributor distributor : distributors) {
                if (!distributor.isBankrupt()) {
                    minDistributor = distributor;
                }
            }
        }
        long costContract;
        if (minDistributor.getNumberOfClients() != 0) {
            costContract = Math.round(Math.floor(minDistributor.getInfrastructureCost()
                    / minDistributor.getNumberOfClients()))
                    + minDistributor.getProductionCost()
                    + minDistributor.getProfit();
        } else {
            costContract = minDistributor.getInfrastructureCost()
                    + minDistributor.getProductionCost()
                    + minDistributor.getProfit();
        }
        for (Consumer c : newConsumers) {
            c.setActualDistributor(minDistributor);
            c.setSumToPay(costContract);
            c.setMoney(c.getInitialBudget() + c.getMonthlyIncome());
            c.setMonthsToPay(minDistributor.getContractLength() - 1);
            if (c.getMoney() < costContract) {
                c.setInDebt(true);
                c.getDebts().put(c.getActualDistributor(),
                        Math.round(Math.floor(1.2 * c.getSumToPay())));
            } else {
                c.setMoney(c.getMoney() - costContract);
            }
        }
    }

    /**
     * First month for initial consumers
     * @param consumers list of consumers
     * @param distributors list of distributors
     */
    private void firstMonth(final List<Consumer> consumers,
                            final List<Distributor> distributors,
                            final List<Producer> producers) {
        for(Distributor d: distributors) {
            StrategyFactory strategyFactory = new StrategyFactory();
            EnergyStrategy energyStrategy = strategyFactory.createStrategy(d);
            EnergyContext context = new EnergyContext(energyStrategy);
            context.executeStrategy(d, producers);
        }
        Distributor minDistributor =
                Collections.min(distributors, new DistributorsComparator());
        long costContract = minDistributor.getInfrastructureCost()
                + minDistributor.getProductionCost()
                + minDistributor.getProfit();
        for (Consumer c : consumers) {
            c.setActualDistributor(minDistributor);
            c.setSumToPay(costContract);
            c.setMoney(c.getInitialBudget() + c.getMonthlyIncome());
            c.setMonthsToPay(minDistributor.getContractLength() - 1);
            if (c.getMoney() < costContract) {
                c.setInDebt(true);
                c.getDebts().put(c.getActualDistributor(),
                        Math.round(Math.floor(1.2 * c.getSumToPay())));
            } else {
                c.setMoney(c.getMoney() - costContract);
            }
            for (Distributor d : distributors) {
                if (d.getId() == minDistributor.getId()) {
                    d.setNumberOfClients(d.getNumberOfClients() + 1);
                    long contract =  d.getInfrastructureCost()
                            + d.getProductionCost() + d.getProfit();
                    d.getContracts().put(c, contract);
                    break;
                }
            }
        }
        for (Distributor d : distributors) {
            if (!d.getContracts().keySet().isEmpty()) {
                for (Consumer con : d.getContracts().keySet()) {
                    if (!con.isInDebt()) {
                        d.setMoney(d.getMoney() + d.getContracts().get(con));
                    }
                }
            }
            d.setMoney(d.getMoney() + d.getInitialBudget());
            d.setMoney(d.getMoney() - (d.getInfrastructureCost()
                    + d.getProductionCost() * d.getNumberOfClients()));
        }
    }

    /**
     * @param distributors list of distributors
     * @param updates list of cost updates
     */
    private void modifyDistributors(final List<Distributor> distributors,
                                    final List<UpdateDistributorChanges> updates) {
        for (UpdateDistributorChanges u : updates) {
            for (Distributor d : distributors) {
                if (!d.isBankrupt()) {
                    if (d.getId() == u.getId()) {
                        d.setProductionCost(u.getProductionCost());
                        d.setInfrastructureCost(u.getInfrastructureCost());
                    }
                }
            }
        }
    }

    /**
     * The needed changes for contract for one month
     * @param consumers list of consumers
     * @param distributors list of distributors
     * @param indices list of indices for new consumers
     */
    private void checkContracts(final List<Consumer> consumers,
                                final List<Distributor> distributors,
                                final ArrayList<Long> indices) {
        Collections.sort(distributors, new DistributorsComparator());
        for (Consumer c : consumers) {
            if (indices.contains(c.getId())) {
                for (Distributor d : distributors) {
                    if (d.getId() == c.getActualDistributor().getId()) {
                        d.setNumberOfClients(d.getNumberOfClients() + 1);
                        d.getContracts().put(c, c.getSumToPay());
                    }
                }
            } else {
                if (!c.isBankrupt()) {
                    c.setMoney(c.getMoney() + c.getMonthlyIncome());
                    if (c.getMonthsToPay() == 0) {
                        Distributor oldDistributor = null;
                        for (Distributor d : distributors) {
                            if (d.getId() == c.getActualDistributor().getId()) {
                                oldDistributor = d;
                                d.getContracts().remove(c);
                            }
                        }
                        if (c.isInDebt() && !c.isBankrupt()) {
                            long penalty = c.getDebts().get(c.getActualDistributor());
                            findOtherDistributor(distributors, c);
                            penalty += c.getSumToPay();
                            if (c.getMoney() < penalty) {
                                c.setBankrupt(true);
                            } else {
                                c.setMoney(c.getMoney() - penalty);
                                c.setInDebt(false);
                                c.setMonthsToPay(c.getMonthsToPay() - 1);
                            }
                        } else {
                            if (!c.isBankrupt()) {
                                findOtherDistributor(distributors, c);
                                c.setMonthsToPay(c.getMonthsToPay() - 1);
                                if (c.getMoney() < c.getSumToPay()) {
                                    c.setInDebt(true);
                                    c.getDebts().put(c.getActualDistributor(),
                                            Math.round(Math.floor(1.2 * c.getSumToPay())));

                                } else {
                                    c.setMoney(c.getMoney() - c.getSumToPay());
                                }
                            }
                        }
                        for (Distributor d : distributors) {
                            if (d.getId() == oldDistributor.getId()) {
                                d.setNumberOfClients(d.getNumberOfClients() - 1);
                            }
                        }
                    } else {
                        if (!c.getActualDistributor().isBankrupt()) {
                            if (c.isInDebt() && !c.isBankrupt()) {
                                long penalty = c.getDebts().get(c.getActualDistributor())
                                        + c.getSumToPay();
                                if (c.getMoney() < penalty) {
                                    c.setBankrupt(true);
                                } else {
                                    c.setMoney(c.getMoney() - penalty);
                                    c.setMonthsToPay(c.getMonthsToPay() - 1);
                                    c.setInDebt(false);
                                }
                            } else {
                                if (!c.isBankrupt()) {
                                    if (c.getMoney() < c.getSumToPay()) {
                                        c.setInDebt(true);
                                        c.setMonthsToPay(c.getMonthsToPay() - 1);
                                        c.getDebts().put(c.getActualDistributor(),
                                                Math.round(Math.floor(1.2
                                                        * c.getSumToPay())));
                                    } else {
                                        c.setMonthsToPay(c.getMonthsToPay() - 1);
                                        c.setMoney(c.getMoney() - c.getSumToPay());
                                    }
                                }
                            }
                        } else {
                            findOtherDistributor(distributors, c);
                            if (c.getMoney() < c.getSumToPay()) {
                                c.getDebts().put(c.getActualDistributor(),
                                        Math.round(Math.floor(1.2 * c.getSumToPay())));
                                c.setInDebt(true);
                                c.setMonthsToPay(c.getMonthsToPay() - 1);
                            } else {
                                c.setMonthsToPay(c.getMonthsToPay() - 1);
                                c.setMoney(c.getMoney() - c.getSumToPay());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Find other distributor with the best price for contract for the consumer
     * @param distributors list of distributors
     * @param consumer a consumer
     */
    private void findOtherDistributor(final List<Distributor> distributors,
                                      final Consumer consumer) {
        Collections.sort(distributors, new DistributorsComparator());
        Distributor distributor = Collections.min(distributors,
                new DistributorsComparator());
        if (distributor.isBankrupt()) {
            for (Distributor d : distributors) {
                if (!d.isBankrupt()) {
                    distributor = d;
                }
            }
        }
        long costContract;
        if (distributor.getNumberOfClients() == 0) {
            costContract = distributor.getInfrastructureCost()
                    + distributor.getProductionCost()
                    + distributor.getProfit();
        } else {
            costContract = Math.round(Math.floor(distributor.getInfrastructureCost()
                    / distributor.getNumberOfClients()))
                    + distributor.getProductionCost()
                    + distributor.getProfit();
        }
        consumer.setActualDistributor(distributor);
        consumer.setSumToPay(costContract);
        consumer.setMonthsToPay(distributor.getContractLength());
        for (Distributor d : distributors) {
            if (d.getId() == distributor.getId()) {
                d.setNumberOfClients(d.getNumberOfClients() + 1);
                d.getContracts().put(consumer, costContract);
            }
        }
    }

    /**
     * Tax the distributors
     * @param distributors list of distributors
     */
    private void taxDistributors(final List<Distributor> distributors) {
        for (Distributor d : distributors) {
            List<Consumer> remember = new ArrayList<>();
            long totalCost = d.getInfrastructureCost()
                    + d.getProductionCost() * d.getNumberOfClients();
            for (Consumer c : d.getContracts().keySet()) {
                if (!c.isInDebt() && !c.isBankrupt()) {
                    d.setMoney(d.getMoney() + d.getContracts().get(c));
                } else if (c.isBankrupt()) {
                    d.setNumberOfClients(d.getNumberOfClients() - 1);
                    remember.add(c);
                }
            }
            if (!d.isBankrupt()) {
                if (d.getMoney() < totalCost) {
                    d.setBankrupt(true);
                    for (Consumer c : d.getContracts().keySet()) {
                        if (c.getDebts().get(d) != null) {
                            c.getDebts().put(d, 0L);
                        }
                    }
                }
                d.setMoney(d.getMoney() - totalCost);
            }
            for(Consumer r : remember) {
                d.getContracts().remove(r);
            }

        }
    }
}