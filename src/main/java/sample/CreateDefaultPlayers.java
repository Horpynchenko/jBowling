package sample;

/**
 * Created by Dmytro on 24.03.2016.
 * Realization of CreatePlayerInterface.
 * Create a
 */
public class CreateDefaultPlayers implements CreatePlayerInterface {
    private MainGame mainGame;
    private int playersQuantity;
    private String DEF_PLAYER_PREF = "Player-";

    public CreateDefaultPlayers(MainGame mainGame, int playersQuantity) {
        this.mainGame = mainGame;
        this.playersQuantity = playersQuantity;
    }

    public void createPlayers() {
        createDefaultPlayers(playersQuantity);
    }

    /**
     * Method for creating a number of Players with default names like: Player-<Number>
     * All created players will put in the PlayersMap: <String, Player>
     * @param quantity
     */
    public void createDefaultPlayers(int quantity) {
        for (int i = 1; i <= quantity; i++) {
            Player player = new Player(DEF_PLAYER_PREF + i);
            mainGame.getPlayersMap().put(DEF_PLAYER_PREF + i, player);
        }
    }
}
