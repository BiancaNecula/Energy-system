package strategies;

import entities.Distributor;

public final class StrategyFactory {
    /**
     * @param distributor
     * @return
     */
    public EnergyStrategy createStrategy(Distributor distributor) {
        switch (EnergyChoiceStrategyType.fromString(distributor.getProducerStrategy())) {
            case GREEN -> {
                return new GreenStrategy();
            }
            case PRICE -> {
                return new PriceStrategy();
            }
            case QUANTITY -> {
                return new QuantityStrategy();
            }
            default -> {
                throw new IllegalArgumentException("The strategy type is not recognized.");
            }
        }
    }
}
