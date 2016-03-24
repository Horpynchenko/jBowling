package sample;

/**
 * Created by Dmytro on 23.03.2016.
 */
public class MainExecutor {

    public static void startInteractiveGame(){
        // filling players:
        MainGame mainGame = new MainGame();

        mainGame.createPlayer("Timo");
        mainGame.createPlayer("Anton");
        mainGame.createDefaultPlayers(3);

        // filling scores for each player during game:
        mainGame.startGame();

        // show results and winners:
        mainGame.printWinner();

        // next dialog

    }
}
