package nl.xillio.workshop.aspectj.aspects;

public interface PersistentFacet {
    default int getId() {
        return 0;
    }
}
