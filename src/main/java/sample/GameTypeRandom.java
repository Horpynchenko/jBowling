package sample;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Dmytro on 24.03.2016.
 */
public class GameTypeRandom implements GameTypeInterface {

    public int getCurrentPoints(int maxPossiblePoints) {
        int random = 0;
        random = ThreadLocalRandom.current().nextInt(0, maxPossiblePoints + 1);
        return random;
    }

}
