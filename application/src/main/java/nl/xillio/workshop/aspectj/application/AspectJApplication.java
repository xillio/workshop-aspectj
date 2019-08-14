package nl.xillio.workshop.aspectj.application;

import nl.xillio.workshop.aspectj.aspects.PersistentFacetAspect;
import nl.xillio.workshop.aspectj.model.Location;
import nl.xillio.workshop.aspectj.model.ModelRepo;
import org.aspectj.lang.Aspects;

public class AspectJApplication {
    private final ModelRepo model;

    AspectJApplication() {
        model = new ModelRepo();
        Aspects.aspectOf(PersistentFacetAspect.class).setModel(model);
        new DataInitializer().initialize();
    }

    public static void main(String[] args) {
        AspectJApplication app = new AspectJApplication();
        app.doSomeWork();
    }

    private void doSomeWork() {
        Location l = new Location("LaPlace", 33.33D, 33.33D);


        model.getLocations().contains(l);
    }


}
