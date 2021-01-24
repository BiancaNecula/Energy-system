package entities;

public class EntityFactory {
    /**
     * @param type string that defines type of entity
     * @param a variable numbers of parameters for entities constructors
     * @return
     */
    public static Entity createEntity(final String type, final Object...a) {
        switch (type) {
            case "consumer":         return new Consumer((long) a[0], (long) a[1], (long) a[2]);
            case "distributor":      return new Distributor((long) a[0], (long) a[1], (long) a[2],
                    (long) a[3], (long) a[4], (String) a[5]);
            case "producer":         return new Producer((long) a[0], (String) a[1], (long) a[2],
                    (Double) a[3], (long) a[4]);
            default:
                throw new IllegalArgumentException("The entity type "
                        + type + " is not recognized.");
        }

    }
}
