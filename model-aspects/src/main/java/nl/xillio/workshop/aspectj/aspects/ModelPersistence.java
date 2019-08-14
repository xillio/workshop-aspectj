package nl.xillio.workshop.aspectj.aspects;

public interface ModelPersistence {
    int save(PersistentFacet persistable);
    void delete(PersistentFacet persistable);
}
