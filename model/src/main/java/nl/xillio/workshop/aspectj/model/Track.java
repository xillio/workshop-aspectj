package nl.xillio.workshop.aspectj.model;

import nl.xillio.workshop.aspectj.aspects.PersistentFacet;

import java.util.Objects;

/**
 * Represents a specific track at a station.
 */
public class Track implements PersistentFacet {
    private final Location location;
    private final String trackName;
    private final boolean hasFacilities;

    public Track(Location location, String trackName, boolean hasFacilities) {
        this.location = location;
        this.trackName = trackName;
        this.hasFacilities = hasFacilities;
        location.addTrack(this);
    }

    public Location getLocation() {
        return location;
    }

    public String getTrackName() {
        return trackName;
    }

    public boolean isHasFacilities() {
        return hasFacilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Track track = (Track) o;
        return Objects.equals(location, track.location) &&
                Objects.equals(trackName, track.trackName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, trackName);
    }
}
