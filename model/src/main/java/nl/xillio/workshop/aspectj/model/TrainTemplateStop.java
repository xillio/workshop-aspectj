package nl.xillio.workshop.aspectj.model;

import nl.xillio.workshop.aspectj.aspects.ModelElement;

import java.time.Duration;
import java.util.Objects;

/**
 * Represents a stop of a train template at a station's track.
 * Notice the times at which the stop starts and ends are expressed as displacements from the time the train left the first station,
 * so the actual local time the train, for example, stops is stop.getTrain().getDepartureTime() + stop.getArrivalTimeDisplacement().
 */
@ModelElement
public class TrainTemplateStop {
    private final TrainTemplate train;
    private final Track track;
    private final Duration arrivalTimeDisplacement;
    private final Duration departureTimeDisplacement;

    public TrainTemplateStop(TrainTemplate train, Track track, Duration arrivalTimeDisplacement, Duration departureTimeDisplacement) {
        this.train = train;
        this.track = track;
        this.arrivalTimeDisplacement = arrivalTimeDisplacement;
        this.departureTimeDisplacement = departureTimeDisplacement;

        // Train must leave no earlier that it arrives
        assert (getComparableArrivalTime().compareTo(getComparableDepartureTime()) <= 0);

        train.addStop(this);
    }

    public TrainTemplate getTrain() {
        return train;
    }

    public Track getTrack() {
        return track;
    }

    public Duration getComparableArrivalTime() {
        return arrivalTimeDisplacement == null ? Duration.ofMillis(Long.MIN_VALUE) : arrivalTimeDisplacement;
    }

    public Duration getComparableDepartureTime() {
        return departureTimeDisplacement == null ? Duration.ofMillis(Long.MAX_VALUE) : departureTimeDisplacement;
    }

    public Duration getArrivalTimeDisplacement() {
        return arrivalTimeDisplacement;
    }

    public Duration getDepartureTimeDisplacement() {
        return departureTimeDisplacement;
    }

    public boolean isFirst() {
        return arrivalTimeDisplacement == null;
    }

    public boolean isLast() {
        return departureTimeDisplacement == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainTemplateStop that = (TrainTemplateStop) o;
        return Objects.equals(train, that.train) &&
                Objects.equals(track, that.track) &&
                Objects.equals(arrivalTimeDisplacement, that.arrivalTimeDisplacement) &&
                Objects.equals(departureTimeDisplacement, that.departureTimeDisplacement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(train, track, arrivalTimeDisplacement, departureTimeDisplacement);
    }
}
