package sample;

/**
 * Created by Dmytro on 23.03.2016.
 */
public class MainExecutor {

    /**
     *
     * New score getting for players depends of GameTypeInterface realization:
     * - input from console:     mainGame.startGame( new GameTypeConsoleInput() );
     * - randomly:               mainGame.startGame( new GameTypeRandom() );
     * - static for all players: mainGame.startGame( new GameTypeStatic() );
     * - input from GUI:         mainGame.startGame( new GameTypeFromGUI() ); // <- create realization
     */
    public static void startInteractiveGame(){
        // filling players:
        MainGame mainGame = new MainGame();

        mainGame.createPlayer("Timo");
        mainGame.createPlayer("Anton");
        mainGame.createDefaultPlayers(3);

        // filling scores for each player during game:
        mainGame.startGame( new GameTypeRandom() ); // <- Change Interface realization to see difference

        // show results and winners:
        mainGame.printWinner();

        // next dialog

    }
}
