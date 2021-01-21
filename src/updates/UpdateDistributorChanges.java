package updates;

public class UpdateDistributorChanges extends Update {
    private long id;
    private long infrastructureCost;

    public UpdateDistributorChanges(final long id, final long infrastructureCost) {
        this.id = id;
        this.infrastructureCost = infrastructureCost;
    }

    /**
     * @return distributor's id
     */
    public long getId() {
        return id;
    }

    /**
     * @return distributor's infrastructure cost
     */
    public long getInfrastructureCost() {
        return infrastructureCost;
    }

}
