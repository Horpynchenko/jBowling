package sample;

/**
 * Created by Dmytro on 24.03.2016.
 */
public class GameTypeStatic implements GameTypeInterface {
    private int staticScore;

    public GameTypeStatic(int staticScore){
        this.staticScore = staticScore;
    }

    public int getCurrentScore() {
        return staticScore;
    }

}
