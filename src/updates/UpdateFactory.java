package updates;

public class UpdateFactory {
    /**
     * @param type string that defines type of update
     * @param a variable numbers of parameters for updates constructors
     * @return
     */
    public static Update createUpdate(final String type, final long...a) {
        if ("distributorChanges".equals(type)) {
            return new UpdateDistributorChanges(a[0], a[1]);
        }
        if ("producerChanges".equals(type)) {
            return new UpdateProducerChanges(a[0], a[1]);
        }
        throw new IllegalArgumentException("The update type " + type + " is not recognized.");
    }
}
