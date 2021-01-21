package utils;

import entities.Distributor;

import java.util.Comparator;

public class DistributorsComparator implements Comparator<Distributor> {
    /**
     * @param a first distributor
     * @param b second distributor
     * @return the a negative integer, zero, or a positive integer as the first argument is
     * less than, equal to, or greater than the second
     */
    public int compare(final Distributor a, final Distributor b) {
        long contractA, contractB;
        if (a.getNumberOfClients() == 0) {
            contractA = a.getInfrastructureCost() + a.getProductionCost() + a.getProfit();
        } else {
            contractA = Math.round(Math.floor(a.getInfrastructureCost() / a.getNumberOfClients()))
                    + a.getProductionCost() + a.getProfit();
        }
        if (b.getNumberOfClients() == 0) {
            contractB = b.getInfrastructureCost() + b.getProductionCost() + b.getProfit();
        } else {
            contractB = Math.round(Math.floor(b.getInfrastructureCost() / b.getNumberOfClients()))
                    + b.getProductionCost() + b.getProfit();
        }
        return (int) (contractA - contractB);
    }
}
