package sample;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Dmytro on 24.03.2016.
 */
public class GameTypeRandom implements GameTypeInterface {

    public int getCurrentScore() {
        int random = ThreadLocalRandom.current().nextInt(0, MainGame.MAX_FRAME_POINTS+1);
        return random;
    }

}
