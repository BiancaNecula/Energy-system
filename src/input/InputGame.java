package input;

import entities.Consumer;
import entities.Distributor;
import entities.Producer;
import updates.UpdateDistributorChanges;
import updates.UpdateProducerChanges;

import java.util.List;

public final class InputGame {
    private long numberOfTurns;
    private List<Consumer> consumers;
    private List<Distributor> distributors;
    private List<Producer> producers;
    private List<List<Consumer>> newConsumers;
    private List<List<UpdateDistributorChanges>> distributorChanges;
    private List<List<UpdateProducerChanges>> producerChanges;

    private static InputGame instance = null;

    private InputGame() {

    }

    /**
     * @return the only instance for this class
     */
    public static InputGame getInstanceOfInput() {
        if (instance == null) {
            instance = new InputGame();
        }
        return instance;
    }

    /**
     * @param numberOfTurns months
     * @param consumers list of consumers
     * @param distributors list of distributors
     * @param newConsumers list of monthly new consumers
     * @param distributorChanges list of monthly cost changes
     */
    public void init(final long numberOfTurns, final  List<Consumer> consumers,
                     final List<Distributor> distributors,
                     final List<Producer> producers,
                     final List<List<Consumer>> newConsumers,
                     final List<List<UpdateDistributorChanges>> distributorChanges,
                     final List<List<UpdateProducerChanges>> producerChanges) {
        this.numberOfTurns = numberOfTurns;
        this.consumers = consumers;
        this.distributors = distributors;
        this.producers = producers;
        this.newConsumers = newConsumers;
        this.distributorChanges = distributorChanges;
        this.producerChanges = producerChanges;
    }

    /**
     * @return months
     */
    public long getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     * @return the list of consumers
     */
    public List<Consumer> getConsumers() {
        return consumers;
    }

    /**
     * @return the list of distributors
     */
    public List<Distributor> getDistributors() {
        return distributors;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    /**
     * @return the list of new consumers
     */
    public List<List<Consumer>> getNewConsumers() {
        return newConsumers;
    }


    /**
     * @return the list of costs updates
     */
    public List<List<UpdateDistributorChanges>> getDistributorChanges() {
        return distributorChanges;
    }

    public List<List<UpdateProducerChanges>> getProducerChanges() {
        return producerChanges;
    }
}
