package sample;

/**
 * Created by Dmytro on 23.03.2016.
 * Class for managing the main application thread.
 * 1. Initialization ManiGame class.
 * 2. Defining new players.
 * 3. Defining points getting type for players, depends of GameTypeInterface realization:
 * - input from console:     mainGame.startGame( new GameTypeConsoleInput() );
 * - randomly:               mainGame.startGame( new GameTypeRandom() );
 * - static for all players: mainGame.startGame( new GameTypeStatic( <staticPoints in int> ) ); f.e.: new GameTypeStatic(5)
 * - input from GUI:         mainGame.startGame( new GameTypeFromGUI() ); // <- TODO: create GameTypeFromGUI
 * 4. Showing results and winners.
 * 5. 'What next?' dialog
 */
public class MainExecutor {

    /**
     * Main game thread.
     */
    public static void startInteractiveGame(){
        // 1. Initialization ManiGame class.
        MainGame mainGame = new MainGame();

        // 2. Defining new players.
        CreatePlayerInterface cpi;

        // create named players:
        cpi = new CreateNamedPlayer( mainGame, "Timo", "Anton" );
        cpi.createPlayers();

        // create players with default names:
        cpi = new CreateDefaultPlayers( mainGame, 3 );
        cpi.createPlayers();

        // 3. Defining points getting type for players, depends of GameTypeInterface realization:
        mainGame.startGame( new GameTypeRandom() ); // <- Change Interface realization to see difference

        // 4. Showing results and winners:
        mainGame.printWinner();

        // 5. 'What next?' dialog
        // TODO: 24.03.2016 'What next?' dialog

    }
}
