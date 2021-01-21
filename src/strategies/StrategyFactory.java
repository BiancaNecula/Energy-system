package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;


public class StrategyFactory {
    public EnergyStrategy createStrategy(Distributor distributor){
        switch (EnergyChoiceStrategyType.fromString(distributor.getProducerStrategy())){
            case GREEN -> {
                return new GreenStrategy();
            }
            case PRICE -> {
                return new PriceStrategy();
            }
            case QUANTITY -> {
                return new QuantityStrategy();
            }
        }
        throw new IllegalArgumentException("The strategy type is not recognized.");
    }
}
