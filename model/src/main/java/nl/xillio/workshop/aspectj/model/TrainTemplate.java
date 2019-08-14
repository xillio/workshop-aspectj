package nl.xillio.workshop.aspectj.model;

import nl.xillio.workshop.aspectj.aspects.PersistentFacet;
import nl.xillio.workshop.aspectj.model.tools.Tools;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Represents a train trip through different stations abstracting from the actual days it runs and the actual departure time.
 * In this model, we do not deal with that complexity, and assume the train runs everyday and leaves the first station always at the same time
 * everyday.
 * <p>
 * This representation of the train trip is in terms of the stations it stops at.
 */
public class TrainTemplate implements PersistentFacet {
    private static final Comparator<TrainTemplateStop> trainTemplateStopOrder = Comparator.comparing(TrainTemplateStop::getComparableArrivalTime);
    private final String trainNumber;
    private final LocalTime departureTime;
    private final TreeSet<TrainTemplateStop> stops;

    public TrainTemplate(String trainNumber, LocalTime departureTime) {
        this.trainNumber = trainNumber;
        this.departureTime = departureTime;
        this.stops = new TreeSet<>(trainTemplateStopOrder);
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    void addStop(TrainTemplateStop stop) {
        if (stops.stream()
                .anyMatch(s -> Tools.isOverlap(s.getComparableArrivalTime(),
                        s.getComparableDepartureTime(), stop.getComparableArrivalTime(), stop.getComparableDepartureTime()))) {
            throw new RuntimeException("Stop intervals must not overlap");
        }

        stops.add(stop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrainTemplate that = (TrainTemplate) o;
        return Objects.equals(trainNumber, that.trainNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainNumber);
    }
}
