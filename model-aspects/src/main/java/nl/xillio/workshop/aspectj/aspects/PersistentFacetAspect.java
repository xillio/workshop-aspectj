package nl.xillio.workshop.aspectj.aspects;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@Aspect
public class PersistentFacetAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistentFacetAspect.class);
    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Multiple inheritance: any model element is Persistent.
     */
    @DeclareMixin(value = "nl.xillio.workshop.aspectj.aspects.PersistentFacet")
    public PersistentFacet persistentFacet() {
        return new PersistentFacetImpl(new Random().nextInt());
    }

    ;

    /**
     * The constructor of PersistenFacetImpl is intercepted by the pointcut
     * initialization(nl.xillio.workshop.aspectj.aspects.PersistentFacet+.new(..)), but we are only interested
     * in the @ModelElement class constructor.
     */
    @Pointcut("within(nl.xillio.workshop.aspectj.aspects.PersistentFacetImpl)")
    private void inPersistenceFacetConstructor() {
    }

    @Pointcut("execution(nl.xillio.workshop.aspectj.aspects.PersistentFacet+.new(..)) && " +
            "!inPersistenceFacetConstructor() && " +
            "target(persistentFacet)")
    private void modelElementCreation(PersistentFacet persistentFacet) {
    }
    
    @After("modelElementCreation(persistentFacet)")
    public void storeElement(PersistentFacet persistentFacet) {
        LOGGER.info("Adding " + persistentFacet.getClass().getSimpleName() + ":" + persistentFacet);
        model.save(persistentFacet);
    }
}
