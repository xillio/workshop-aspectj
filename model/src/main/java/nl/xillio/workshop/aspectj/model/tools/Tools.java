package nl.xillio.workshop.aspectj.model.tools;

import java.time.Duration;

public class Tools {
    public static boolean isOverlap(Duration from1, Duration to1, Duration from2, Duration to2) {
        //TODO fix for included intervals
        return from1.compareTo(from2) * from1.compareTo(to2) * to2.compareTo(from1) * to2.compareTo(to1) < 0;
    }
}
