package nl.xillio.workshop.aspectj.aspects;

public class PersistentFacetImpl implements PersistentFacet {
    private int id;

    public PersistentFacetImpl(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
