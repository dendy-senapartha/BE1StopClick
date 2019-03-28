package com.Be1StopClick.util;

/*
 * Created by dendy-prtha on 25/03/2019.
 * utility for user ID
 */

import java.util.Random;

public class IdUtility {

    public static String generateUniqueID() {

        String uniqID = "";
        int maxBound = 9;
        int numbersNeeded = 20;
        Random rng = new Random();
        while (uniqID.length() < numbersNeeded) {
            Integer next = rng.nextInt(maxBound);
            // As we're adding to a set, this will automatically do a containment check
            uniqID += next;
        }
        return uniqID;
    }
}
