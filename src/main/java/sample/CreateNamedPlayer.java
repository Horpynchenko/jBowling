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

    /**
     * Method for create named players, signed in variable "names"
     */
    public void createPlayers() {
        if (names != null) {
            if (!names[0].equalsIgnoreCase("") ) {
                for (int i = 0; i < names.length; i++) {
                    createNamedPlayer(names[i]);
                }
            }
        }
    }

    /**
     * Method for creating single Player with defined name.
     * Created player will be put in the PlayersMap: <String, Player>
     * @param name
     */
    public void createNamedPlayer(String name){
        Player player = new Player(name);
        mainGame.getPlayersMap().put(name, player);
    }

}


