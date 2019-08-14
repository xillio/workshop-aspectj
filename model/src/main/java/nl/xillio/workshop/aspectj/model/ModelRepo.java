package nl.xillio.workshop.aspectj.model;

import nl.xillio.workshop.aspectj.aspects.Model;
import nl.xillio.workshop.aspectj.aspects.PersistentFacet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * This represents the Domain Model and is supposed to he a closed dataset, i.e. we assume there is no relevant domain object
 * that is not known to the Model.
 */
public class ModelRepo implements Model {
    private final Set<Location> locations = new HashSet<>();
    private final Set<Track> tracks = new HashSet<>();
    private final Set<TrainTemplate> trainTemplates = new HashSet<>();
    private final Set<TrainTemplateStop> trainTemplateStops = new HashSet<>();

    @Override
    public int save(PersistentFacet persistentElement) {
        // here multiple dispatch would help
        if(persistentElement instanceof Location) {
            addDomainObject((Location)persistentElement);
        } else if(persistentElement instanceof Track) {
            addDomainObject((Track)persistentElement);
        } else if(persistentElement instanceof TrainTemplate) {
            addDomainObject((TrainTemplate)persistentElement);
        } else if(persistentElement instanceof TrainTemplateStop) {
            addDomainObject((TrainTemplateStop)persistentElement);
        } else {
            throw new RuntimeException("unknown element");
        }

        return persistentElement.getId();
    }

    /* REPOSITORY METHODS */

    public void addDomainObject(Location location) {
        boolean isNew = locations.add(location);

        if (!isNew) {
            throw new RuntimeException("Duplicate!");
        }
    }

    public void addDomainObject(Track track) {
        boolean isNew = tracks.add(track);

        if (!isNew) {
            throw new RuntimeException("Duplicate!");
        }
    }

    public void addDomainObject(TrainTemplate trainTemplate) {
        boolean isNew = trainTemplates.add(trainTemplate);

        if (!isNew) {
            throw new RuntimeException("Duplicate!");
        }
    }

    public void addDomainObject(TrainTemplateStop stop) {
        boolean isNew = trainTemplateStops.add(stop);

        if (!isNew) {
            throw new RuntimeException("Duplicate!");
        }
    }

    public Set<TrainTemplate> getTrainTemplates() {
        return Collections.unmodifiableSet(trainTemplates);
    }

    public Set<Location> getLocations() {
        return locations;
    }
}
