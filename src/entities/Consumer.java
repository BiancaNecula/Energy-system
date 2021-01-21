package entities;

import java.util.HashMap;
import java.util.Map;

public class Consumer extends Entity {
    private final long id;
    private final long initialBudget;
    private final long monthlyIncome;
    private Distributor actualDistributor;
    private long monthsToPay;
    private long sumToPay;
    private boolean inDebt = false;
    private long money;
    private boolean isBankrupt = false;
    private Map<Distributor, Long> debts = new HashMap();

    public Consumer(final long id, final long initialBudget, final long monthlyIncome) {
        this.id = id;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * @return consumer's debts
     */
    public Map<Distributor, Long> getDebts() {
        return debts;
    }

    /**
     * @return consumer's budget
     */
    public long getMoney() {
        return money;
    }

    /**
     * @param money new budget
     */
    public void setMoney(final long money) {
        this.money = money;
    }

    /**
     * @return consumer's id
     */
    public long getId() {
        return id;
    }

    /**
     * @return consumer's initial budget
     */
    public long getInitialBudget() {
        return initialBudget;
    }

    /**
     * @return consumer's monthly income
     */
    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * @return consumer's distributor with which he has contract
     */
    public Distributor getActualDistributor() {
        return actualDistributor;
    }

    /**
     * @return true if is in debt, false otherway
     */
    public boolean isInDebt() {
        return inDebt;
    }

    /**
     * @param inDebt true if is in debt, false otherway
     */
    public void setInDebt(final boolean inDebt) {
        this.inDebt = inDebt;
    }

    /**
     * @param actualDistributor consumer's distributor with which he has contract
     */
    public void setActualDistributor(final Distributor actualDistributor) {
        this.actualDistributor = actualDistributor;
    }

    /**
     * @return number of rates the consumer's has to pay
     */
    public long getMonthsToPay() {
        return monthsToPay;
    }

    /**
     * @param monthsToPay number of rates the consumer's has to pay
     */
    public void setMonthsToPay(final long monthsToPay) {
        this.monthsToPay = monthsToPay;
    }

    /**
     * @return the monthly rate the consumer's has to pay
     */
    public long getSumToPay() {
        return sumToPay;
    }

    /**
     * @param sumToPay the monthly rate the consumer's has to pay
     */
    public void setSumToPay(final long sumToPay) {
        this.sumToPay = sumToPay;
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

    /**
     * @return pretty print
     */
    @Override
    public String toString() {
        return "Consumer{"
                + "id=" + id
                + ", initialBudget=" + initialBudget
                + ", monthlyIncome=" + monthlyIncome
                + '}';
    }
}
