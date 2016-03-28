package sample;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Dmytro on 24.03.2016.
 */
public class GameTypeConsoleInput implements GameTypeInterface{

    public int getCurrentPoints(int maxPossiblePoints) throws IOException {
        int points = 0;
        boolean noMatch = true;
        do {
            System.out.print("Enter your Points (0 - " + maxPossiblePoints + ") or 'q' for skip: ");
            try {
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                String str = br.readLine();

                try {
                    if (str.equalsIgnoreCase("q")) {
                        noMatch = false;
                        points = 0;
                    } else {
                        points = Integer.valueOf(str).intValue();
                        if (points >= 0 && points <= maxPossiblePoints) {
                            noMatch = false;
                        } else {
                            System.out.println("Incorrect number! Valid number in range from 0 to " + maxPossiblePoints + ".");
                        }
                    }

                } catch (NumberFormatException nfe) {
                    System.out.println("Incorrect format! Must be a integer digit in range from 0 to " + maxPossiblePoints + ".");
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } while (noMatch);

        return points;
    }

}
