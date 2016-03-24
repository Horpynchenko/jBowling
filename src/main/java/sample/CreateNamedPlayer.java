package sample;

/**
 * Created by Dmytro on 24.03.2016.
 */
public class CreateNamedPlayer implements CreatePlayerInterface {
    private MainGame mainGame;
    private String[] names;

    public CreateNamedPlayer(MainGame mainGame, String... names) {
        this.mainGame = mainGame;
        this.names = names;
    }

    public void createPlayers() {
        if (names != null) {
            if (!names[0].equalsIgnoreCase("") ) {
                for (int i = 0; i < names.length; i++) {
                    mainGame.createPlayer(names[i]);
                }
            }
        }
    }
}


