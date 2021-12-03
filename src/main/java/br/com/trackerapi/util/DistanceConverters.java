package br.com.trackerapi.util;

public class DistanceConverters {

    public final static double KM_UNIT = 1000d;

    public static double metersToKm(double meters) {
        return meters / KM_UNIT;
    }

    public static double kmToMeters(double kms) {
        return kms * KM_UNIT;
    }
}
