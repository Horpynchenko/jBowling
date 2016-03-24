package sample;

/**
 * Created by Dmytro on 23.03.2016.
 */
public class MainExecutor {

    /**
     *
     * New getting scores for players, depends of GameTypeInterface realization:
     * - input from console:     mainGame.startGame( new GameTypeConsoleInput() );
     * - randomly:               mainGame.startGame( new GameTypeRandom() );
     * - static for all players: mainGame.startGame( new GameTypeStatic() );
     * - input from GUI:         mainGame.startGame( new GameTypeFromGUI() ); // <- create realization
     */
    public static void startInteractiveGame(){
        // filling players:
        MainGame mainGame = new MainGame();

        CreatePlayerInterface cpi = new CreateNamedPlayer(mainGame, "Timo", "Anton");
        cpi.createPlayers();

        cpi = new CreateDefaultPlayers(mainGame, 3);
        cpi.createPlayers();

        // filling scores for each player during game:
        mainGame.startGame( new GameTypeRandom() ); // <- Change Interface realization to see difference

        // show results and winners:
        mainGame.printWinner();

        // TODO: 24.03.2016 next dialog

    }
}
