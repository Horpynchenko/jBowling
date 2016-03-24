package sample;

/**
 * Created by Dmytro on 24.03.2016.
 */
public class CreateDefaultPlayers implements CreatePlayerInterface {
    private MainGame mainGame;
    private int playersQuantity;

    public CreateDefaultPlayers(MainGame mainGame, int playersQuantity) {
        this.mainGame = mainGame;
        this.playersQuantity = playersQuantity;
    }

    public void createPlayers() {
        mainGame.createDefaultPlayers(playersQuantity);
    }
}
