package sample;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Dmytro on 24.03.2016.
 */
public class GameTypeConsoleInput implements GameTypeInterface{

    @Override
    public int getCurrentScore() throws IOException {
        int score = -1;
        boolean noMatch = true;
        do {
            System.out.print("Enter your Score (0 - 10) or 'q' for skip: ");
            try {

                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                String str = br.readLine();

                try {

                    if (str.equalsIgnoreCase("q")) {
                        noMatch = false;
                        score = 0;
                    } else {
                        score = Integer.valueOf(str).intValue();
                        if (score >= 0 && score <= 10) {
                            noMatch = false;
                        } else {
                            System.out.println("Incorrect number! Valid number in range from 0 tp 10.");
                        }
                    }

                } catch (NumberFormatException nfe) {
                    System.out.println("Incorrect format! Must be a integer digit in range from 0 tp 10.");
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } while (noMatch);

        return score;
    }

}
