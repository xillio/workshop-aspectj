package nl.xillio.workshop.aspectj.application;

import nl.xillio.workshop.aspectj.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;

public class DataInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    private Location DEN_HELDER;
    private Location AMSTERDAM_CENTRAAL;
    private Location UTRECHT_CENTRAAL;
    private Location ROTTERDAM_CENTRAAL;
    private Location GRONIGEN;

    public void initialize() {
        LOGGER.info("Initializing train data");

        makeLocations();

        addTracks(DEN_HELDER, 10);
        addTracks(AMSTERDAM_CENTRAAL, 32);
        addTracks(UTRECHT_CENTRAAL, 25);
        addTracks(ROTTERDAM_CENTRAAL, 22);
        addTracks(GRONIGEN, 7);

        TrainTemplate denHelderUtrecht = new TrainTemplate("3", LocalTime.of(10, 33));

        new TrainTemplateStop(denHelderUtrecht, getTrack(DEN_HELDER, "Track 3"), null, Duration.ZERO);
        new TrainTemplateStop(denHelderUtrecht, getTrack(AMSTERDAM_CENTRAAL, "Track 10"), Duration.ofMinutes(111), Duration.ofMinutes(114));
        new TrainTemplateStop(denHelderUtrecht, getTrack(UTRECHT_CENTRAAL, "Track 22"), Duration.ofMinutes(149), null);

        TrainTemplate rotterdamGronigen = new TrainTemplate("42", LocalTime.of(9, 45));

        new TrainTemplateStop(rotterdamGronigen, getTrack(ROTTERDAM_CENTRAAL, "Track 1"), null, Duration.ZERO);
        new TrainTemplateStop(rotterdamGronigen, getTrack(UTRECHT_CENTRAAL, "Track 13"), Duration.ofMinutes(47), Duration.ofMinutes(49));
        new TrainTemplateStop(rotterdamGronigen, getTrack(AMSTERDAM_CENTRAAL, "Track 30"), Duration.ofMinutes(92), Duration.ofMinutes(94));
        new TrainTemplateStop(rotterdamGronigen, getTrack(GRONIGEN, "Track 5"), Duration.ofMinutes(222), null);

        LOGGER.info("DONE");
    }

    private Track getTrack(Location location, String name) {
        return location.getTracks().stream()
                .filter(t -> t.getTrackName().equals(name))
                .findAny()
                .get();
    }

    private void addTracks(Location station, int numberOfTracks) {
        for (int i = 0; i < numberOfTracks; i++) {
            new Track(station, "Track " + i, false);
        }
    }

    private void makeLocations() {
        DEN_HELDER = new Location("Den Helder", 52.95206611842683, 4.763307957247274);
        AMSTERDAM_CENTRAAL = new Location("Amsterdam Centraal", 52.378633, 4.899743);
        UTRECHT_CENTRAAL = new Location("Utrecht", 52.090384, 5.109308);
        ROTTERDAM_CENTRAAL = new Location("Rotterdam Centraal", 51.925597, 4.470921);
        GRONIGEN = new Location("Gronigen", 53.210524, 6.564400);
    }
}
