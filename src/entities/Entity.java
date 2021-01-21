package entities;

public abstract class Entity {
    private long id;
    private long initialBudget;

    /**
     * @return entity id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id entity id
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * @return entity initial budget
     */
    public long getInitialBudget() {
        return initialBudget;
    }

    /**
     * @param initialBudget entity initial budget
     */
    public void setInitialBudget(final long initialBudget) {
        this.initialBudget = initialBudget;
    }
}
