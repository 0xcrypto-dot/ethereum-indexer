package com.deukyunlee.indexer.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 26.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtil {
    private static final String ZERO_X = "0x";
    private static final String UTC = "UTC";


    /**
     * Converts a hexadecimal UNIX timestamp string to an Instant.
     *
     * @param hex the hexadecimal string representing a UNIX timestamp
     * @return the Instant representation of the timestamp
     */
    public static Instant convertHexToInstant(String hex) {
        if (hex.startsWith(ZERO_X)) {
            hex = hex.substring(2);
        }

        long unixTimestamp = Long.parseLong(hex, 16);

        return Instant.ofEpochSecond(unixTimestamp);
    }
}
