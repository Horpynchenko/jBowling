package sample;

/**
 * Created by Dmytro on 24.03.2016.
 */
public class GameTypeStatic implements GameTypeInterface {
    private int staticPoints;

    public GameTypeStatic(int staticPoints){
        this.staticPoints = staticPoints;
    }

    public int getCurrentPoints(int maxPossiblePoints) {
        return staticPoints;
    }

}
