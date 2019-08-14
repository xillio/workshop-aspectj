package nl.xillio.workshop.aspectj.model;

import nl.xillio.workshop.aspectj.aspects.PersistentFacet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a station.
 */
public class Location implements PersistentFacet {
    private final String name;
    private final double longitude;
    private final double latitude;
    private final Set<Track> tracks;

    public Location(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.tracks = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }

    public void addTrack(Track track) {
        if (track.getLocation() == this) {
            tracks.add(track);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Location location = (Location) o;
        return Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
