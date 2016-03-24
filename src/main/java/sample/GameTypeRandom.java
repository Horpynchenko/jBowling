package sample;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Dmytro on 24.03.2016.
 */
public class GameTypeRandom implements GameTypeInterface {

    @Override
    public int getCurrentScore() {
        int random = ThreadLocalRandom.current().nextInt(0, 11);
        return random;
    }

}
