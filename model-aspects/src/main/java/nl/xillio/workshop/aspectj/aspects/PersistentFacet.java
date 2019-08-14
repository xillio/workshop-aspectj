package nl.xillio.workshop.aspectj.aspects;

public interface PersistentFacet {
    int getId();
    void save(ModelPersistence model);
}
