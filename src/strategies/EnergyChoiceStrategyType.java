package strategies;

/**
 * Strategy types for distributors to choose their producers
 */
public enum EnergyChoiceStrategyType {
    GREEN("GREEN"),
    PRICE("PRICE"),
    QUANTITY("QUANTITY");
    public final String label;

    EnergyChoiceStrategyType(String label) {
        this.label = label;
    }

    public static EnergyChoiceStrategyType fromString(String text) {
        for (EnergyChoiceStrategyType commandType : EnergyChoiceStrategyType.values()) {
            if (commandType.label.equalsIgnoreCase(text)) {
                return commandType;
            }
        }
        return null;
    }
}
